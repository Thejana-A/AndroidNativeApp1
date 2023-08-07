package com.example.androidnativeapp1.translator

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Camera
import android.net.Uri
import android.nfc.Tag
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.core.SurfaceRequest
import androidx.camera.video.VideoCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.OutputResults
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoOutput
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.core.net.toUri
import androidx.core.util.Consumer
import com.example.androidnativeapp1.R
import com.example.androidnativeapp1.utilities.CameraActivity
import com.example.androidnativeapp1.utilities.RunSkeleonExtraction
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class OngoingSessionView : AppCompatActivity() {

    private var imageCapture: ImageCapture? = null
    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var outputResult: OutputResults? = null

    private var isRecording: Boolean = false
    private var isLeaveSession: Boolean = false

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ongoing_session_view)

        // hide the action bar
        supportActionBar?.hide()

        // Check camera permissions if all permission granted
        // start camera else ask for the permission
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        // set on click listener for the button of capture photo
        // it calls a method which is implemented below
        findViewById<Button>(R.id.leaveSession).setOnClickListener {
            captureVideoForFourSeconds();
//            isLeaveSession = true
            // pop the current activity from the stack
//            finish()
        }

        // TODO: set on click listener for the button of leave session @Thejana-A
        // TODO: call the video capture method every 4 seconds and run the skeleton extraction in background thread @Thejana-A
        // TODO: implement all the HTTP requests to send the data to the server @Thejana-A
        // TODO: implement the UI for the ongoing session view @Thejana-A

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onResume() {
        super.onResume()
//        while(!isLeaveSession) {
//            captureVideoForFourSeconds()
//        }
    }

    // Function: Start Camera
    // This function is used to start and initialize the camera
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview of the camera
            val preview = Preview.Builder()
                .build()
                .also {
                    val viewBinding = findViewById<PreviewView>(R.id.viewFinder)
                    it.setSurfaceProvider(viewBinding.surfaceProvider)
                }

            // Video recorder configuration
            val recorder = Recorder.Builder()
                .setQualitySelector(QualitySelector.from(Quality.HIGHEST))
                .build()
            videoCapture = VideoCapture.withOutput(recorder)

            // Select front camera as a default camera
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()
                // Bind use cases to camera and start the preview
                cameraProvider
                    .bindToLifecycle(this, cameraSelector, preview, videoCapture)
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    // Function: Capture Video
    // This function is used to capture the video
    private fun captureVideo() {
        val videoCapture = this.videoCapture ?: return

        // Stop recording if it is already recording
        val curRecording = recording
        if (curRecording != null) {
            curRecording.stop();
            recording = null
            return
        }

        // Create a video file name
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        // Create the media store output options
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video")
            }
        }

        // Create the output options object which contains the file + metadata
        val mediaStoreOutputOptions = MediaStoreOutputOptions
            .Builder(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues)
            .build()

        // recording object and start recording
        recording = videoCapture.output
            .prepareRecording(this, mediaStoreOutputOptions)
            .start(ContextCompat.getMainExecutor(this))
            { recordEvent ->
                when (recordEvent) {

                    // after recording is over
                    is VideoRecordEvent.Finalize -> {
                        if (!recordEvent.hasError()) {
                            val msg =
                                "Video capture succeeded: ${recordEvent.outputResults.outputUri}"
                            outputResult = recordEvent.outputResults

                            isRecording = false

                            // run the skeleton extraction in background thread and send the result to firestore
                            RunSkeleonExtraction().runSkeleonExtractionInBackgroundThread(
                                recordEvent.outputResults.outputUri,
                                this
                            )

                            // delete the video file from the media store
                            val contentResolver = this.contentResolver
                            val uriToDelete = recordEvent.outputResults.outputUri
                            contentResolver.delete(uriToDelete, null, null)

                        } else {
                            recording?.close()
                            recording = null
                            Log.e(TAG, "Video capture ends with error: ${recordEvent.error}")
                        }
                    }
                }
            }
    }

    // Function: Capture Video For Four Seconds
    // This function is used to capture the video for four seconds
    private fun captureVideoForFourSeconds() {
        isRecording = true
        captureVideo();

        // stop recording after 4 seconds (for some reason it takes 5 seconds to record 4 seconds video)
        Handler().postDelayed({
            recording?.stop()
            Log.d(TAG, "uri: ${recording.toString()}")
        }, 5000)
    }

    // Function: Handle Video Recording
    // This function is used to handle the video recording
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    // creates a folder inside internal storage
    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    // checks the camera permission
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            // If all permissions granted , then start Camera
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                // If permissions are not granted,
                // present a toast to notify the user that
                // the permissions were not granted.
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }

    // companion object
    companion object {
        private const val TAG = "CameraXGFG"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 20
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    // Function: On Destroy
    // This function is used to destroy the camera
    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
