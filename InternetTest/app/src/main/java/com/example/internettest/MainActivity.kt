package com.example.internettest
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.net.SocketException
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private val TAG = "Flickr cats"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permission = Manifest.permission.INTERNET
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(permission), 1)
        }
    }

    fun onClick(view: View) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            try {
                val url =
                    URL("https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")
                val connection = url.openConnection()
                val response = StringBuilder()
                connection.getInputStream().bufferedReader().useLines { lines ->
                    lines.forEach {
                        response.append(it)
                    }
                }
                Log.d(TAG, response.toString())
            } catch (e: SocketException) {
                Log.d(TAG, "SocketException occurred. Make sure the app has the required permission.")
            } catch (e: Exception) {
                Log.d(TAG, "Exception occurred: ${e.message}")
            }
        }
    }
}
