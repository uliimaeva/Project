package tat.neft.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import tat.neft.R
import tat.neft.files.ConfigWorker
import tat.neft.files.MyFile
import java.io.File
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths


class TestActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        ActivityCompat.requestPermissions(
            this, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
    }

    @SuppressLint("IntentReset")
    @RequiresApi(Build.VERSION_CODES.Q)
    fun buttonCreateFile(view: View?) {
        val config = ConfigWorker(applicationContext)
        for (app in config.readConfig()) {
            Log.wtf("CONFIG", "aboba " + app.url)
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.Q)
    fun buttonOpenFile(view: View?) {
        val config = ConfigWorker(applicationContext)
        config.addOption(MyFile("", "AAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"))
        //val intent = Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI)
        //        intent.setType("application/pdf");
        //intent.type = "*/*"
        //this.startActivity(intent)
    }
}

//fun xmlFileExport() {
//    val fileName = "q.txt"
//    val fileContent = "icon test.xml\nname cruaso\nurl www.google.com"
//
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Downloads.DISPLAY_NAME, fileName)
//            put(MediaStore.Downloads.MIME_TYPE, "application/txt")
//            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
//        }
//        contentValues.put(MediaStore.Downloads.RELATIVE_PATH, "Download/" + "test")
//        val resolver = this.contentResolver
//
//        val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
//        Toast.makeText(this, "successful", Toast.LENGTH_LONG).show()
//
//        if (uri != null) {
//            val fileOutputStream = resolver.openOutputStream(uri)
//            fileOutputStream?.write(fileContent.toByteArray())
//            fileOutputStream?.close()
//        } else {
//            Toast.makeText(this, "Can\'t resolve media path", Toast.LENGTH_LONG).show()
//
//        }
//    }
//}