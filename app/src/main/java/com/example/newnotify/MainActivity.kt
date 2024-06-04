package com.example.newnotify

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var requestPermissionlaunher: ActivityResultLauncher<String>

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*   ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
               val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
               v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
               insets
           }*/
        if (intent.extras != null) {
            // Process extras from notification if needed
            Log.e(TAG, "onCreate: not null ${intent.extras}", )
        }



        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            Log.e(TAG, "onCreate: $token", )

            // Log and toast
            // val msg = getString(R.string.msg_token_fmt, token)
            //   Log.d(TAG, msg)
            // Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        requestPermissionlaunher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                // Permission is granted
                Log.e(TAG, "onCreate: permission granted")
            } else {
                Log.e(TAG, "onCreate: permission not granted")
            }
        }

        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionlaunher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        lifecycleScope.launch(Dispatchers.IO) {
         //   val accessToken=AccessToken()
           val data= AccessToken1.getAccessToken()
            Log.e(TAG, "accesstoken is: $data", )
        }
     //   val accesstoken=getAccessToken()
        //Log.e(TAG, "accesstoken: $accesstoken", )
    }

/*    private fun getAccessToken(): String? {
        var accessToken: String? = null
        try {
            val inputStream: InputStream = assets.open("chatpp.json")
            val googleCredentials = GoogleCredentials.fromStream(inputStream)
                .createScoped(listOf("https://www.googleapis.com/auth/firebase.messaging"))
            googleCredentials.refreshIfExpired()
            accessToken = googleCredentials.accessToken.tokenValue
        } catch (e: IOException) {
            Log.e(TAG, "Error reading credentials file", e)
        } catch (e: Exception) {
            Log.e(TAG, "Unexpected error", e)
        }

        return accessToken
    }*/
}