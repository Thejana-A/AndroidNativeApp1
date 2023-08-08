package com.example.androidnativeapp1.utilities

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.mediapipe.tasks.vision.core.RunningMode

class RunSkeleonExtraction {

    private lateinit var poseLandmarkerHelper: SkeletonExtraction
    private lateinit var extractedResult: SkeletonExtraction.ResultBundle

    // Function: runSkeleonExtraction
    // Description: This function runs the skeleton extraction on the videoUri passed in.
    fun runSkeleonExtraction(videoUri: Uri, context: Context) {

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
        extractedResult = poseLandmarkerHelper.detectVideoFile(videoUri, 1000)!!

        // Send the resultObject to Firestore
        sendResultObjectToFireStore(extractedResult)
    }

    fun getExtractedResult(): SkeletonExtraction.ResultBundle {
        return extractedResult
    }

    // Function: runSkeleonExtractionInBackgroundThread
    // Description: This function runs the skeleton extraction on the videoUri passed in on a background thread.
    fun runSkeleonExtractionInBackgroundThread(videoUri: Uri, context: Context) {

        // Create a new thread to run the skeleton extraction on
        val thread = Thread(Runnable {
            // Run the skeleton extraction on the videoUri passed in
            runSkeleonExtraction(videoUri, context)
        })
        // Start the thread and wait for it to finish
        thread.start()
        thread.join()
    }

    // Function: sendResultObjectToFireStore
    // Description: This function sends the resultObject to Firestore.
    fun sendResultObjectToFireStore(resultObject: SkeletonExtraction.ResultBundle) {

        // Get a reference to the Firestore instance
        val firestore = FirebaseFirestore.getInstance() // Get a reference to the Firestore instance

        // Create a new document in a Firestore collection (replace "results" with your desired collection name)
        val collectionReference = firestore.collection("resultObjects")
        val documentReference = collectionReference
            .document()

        // Convert the resultObject to a Map or any suitable data structure for Firestore
        val datatobeStored = resultObject.toString()
        val resultData = hashMapOf(
            "ResultObjects" to datatobeStored
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
}
