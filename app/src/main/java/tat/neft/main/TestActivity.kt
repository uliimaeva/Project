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
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import tat.neft.R
import java.io.File


class TestActivity : AppCompatActivity() {
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
        xmlFileExport()
//        val intent =
//            Intent(Intent.ACTION_CREATE_DOCUMENT, MediaStore.Downloads.EXTERNAL_CONTENT_URI)
//        //        intent.setType("application/pdf");
//        intent.type = "*/*"
//        this.startActivity(intent)
    }

    @SuppressLint("IntentReset")
    @RequiresApi(Build.VERSION_CODES.Q)
    fun buttonOpenFile(view: View?) {
        val fileName = "Download/test.txt"
        val lines: List<String> = File(fileName).readLines()
        lines.forEach { line -> Toast.makeText(this, line, Toast.LENGTH_SHORT).show() }
//        val intent = Intent(Intent.ACTION_VIEW, MediaStore.Downloads.EXTERNAL_CONTENT_URI)
//        //        intent.setType("application/pdf");
//        intent.type = "*/*"
//        this.startActivity(intent)
    }

    fun xmlFileExport() {
        val fileName = "test.txt"
        val fileContent = "test file \nfor download";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // API version >= 29 (Android 10, 11, ...)

            val contentValues = ContentValues().apply {
                put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                put(MediaStore.Downloads.RELATIVE_PATH, "Download/test")
                put(MediaStore.Downloads.MIME_TYPE, "application/txt")
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }
            val resolver = this.contentResolver

            // регистрация файла
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            Toast.makeText(this, "successful", Toast.LENGTH_LONG).show()

            if (uri != null) {
                val fileOutputStream = resolver.openOutputStream(uri)
                fileOutputStream?.write(fileContent.toByteArray())
                fileOutputStream?.close()

            } else {
                Toast.makeText(this, "Can\'t resolve media path", Toast.LENGTH_LONG).show()

            }
        }
    }
}