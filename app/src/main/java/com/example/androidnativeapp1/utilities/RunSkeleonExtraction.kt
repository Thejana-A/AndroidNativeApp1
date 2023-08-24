package com.example.androidnativeapp1.utilities

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.mediapipe.tasks.vision.core.RunningMode
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class RunSkeleonExtraction {

    private lateinit var poseLandmarkerHelper: SkeletonExtraction
    private lateinit var extractedResult: SkeletonExtraction.ResultBundle
    private val client = OkHttpClient()

    // Function: runSkeleonExtraction
    // Description: This function runs the skeleton extraction on the videoUri passed in.
    fun runSkeleonExtraction(sessionID: String, videoUri: Uri, context: Context) {

        // Create a new instance of the SkeletonExtraction class
        // The parameters are the minimum detection confidence 0.5f, minimum tracking confidence 0.5f, and minimum pose confidence 0.5f, Full body model, CPU delegate, and Video mode
        poseLandmarkerHelper = SkeletonExtraction(
            0.5f,
            0.5f,
            0.5f,
            SkeletonExtraction.MODEL_POSE_LANDMARKER_FULL,
            SkeletonExtraction.DELEGATE_CPU,
            RunningMode.VIDEO,
            context,
        )

        // Run the skeleton extraction on the videoUri passed in
        extractedResult = poseLandmarkerHelper.detectVideoFile(videoUri, 33)!!

        // Send the resultObject to Firestore

        sendResultObjectToFireStore(sessionID, extractedResult)
    }

    fun getExtractedResult(): SkeletonExtraction.ResultBundle {
        return extractedResult
    }

    // Function: runSkeleonExtractionInBackgroundThread
    // Description: This function runs the skeleton extraction on the videoUri passed in on a background thread.
    fun runSkeleonExtractionInBackgroundThread(sessionID : String, videoUri: Uri, context: Context) {

        // Create a new thread to run the skeleton extraction on
        val thread = Thread(Runnable {
            // Run the skeleton extraction on the videoUri passed in
            runSkeleonExtraction(sessionID, videoUri, context)
        })
        // Start the thread and wait for it to finish
        thread.start()
        thread.join()
    }

    // Function: sendResultObjectToFireStore
    // Description: This function sends the resultObject to Firestore.
    fun sendResultObjectToFireStore(sessionID:String, resultObject: SkeletonExtraction.ResultBundle) {
        val userID = "f69deaaf-5c41-4ba2-8bad-e44e026b516b"
        val urlToApi = "https://api-be-my-voice.azurewebsites.net/api/translation/create-translation"
        val requestBody = "{ \"sessionID\":\"$sessionID\", \"userID\":\"$userID\", \"resultObjectFromSkeleton\":\"$resultObject\", \"userType\":\"muteuser\" }"

        // Get a reference to the Firestore instance
        val firestore = FirebaseFirestore.getInstance() // Get a reference to the Firestore instance
        apiCallCreateTranslation(urlToApi, requestBody)
        // Create a new document in a Firestore collection (replace "results" with your desired collection name)
        val collectionReference = firestore.collection("resultObjects")
        val documentReference = collectionReference
            .document()
        val gson = Gson()
        val json = gson.toJson(resultObject)

        // Convert the resultObject to a Map or any suitable data structure for Firestore

        val resultData = hashMapOf(
            "ResultObjects" to json,
            "createdAt" to System.currentTimeMillis()
        )

        // Set the data of the document to the resultData
        documentReference.set(resultData)
            .addOnSuccessListener {
                Log.i("RunSkeleonExtraction", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.e("RunSkeleonExtraction", "Error writing document", e)
            }
    }

    fun apiCallCreateTranslation(urlString: String, requestBody: String): String {
        val mediaType = "application/json; charset=utf-8".toMediaType()

        val request = Request.Builder()
            .url(urlString)
            .post(requestBody.toRequestBody(mediaType))
            .build()

        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            return response.body.string() ?: ""
        } else {
            throw Exception("HTTP POST request failed with response code: ${response.code}")
        }
    }
}
