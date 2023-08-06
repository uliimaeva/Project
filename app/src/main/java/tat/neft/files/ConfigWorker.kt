package tat.neft.files

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.requestPermissions
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileInputStream


class ConfigWorker(val activity: Activity) {
    private val CONFIG_PATH: String = "/storage/emulated/0/Download"

    fun readConfig(): MutableList<MyFile> {
        requestPermissions(
            activity, arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )
        val res: MutableList<MyFile> = mutableListOf()
        val configDir = File(CONFIG_PATH)
        val files = configDir.listFiles()
        for (file in files!!) {
            Log.d("JOPA", "JOPA " + file.name + " " + file.extension)
            if (!(file.isFile && file.extension == "json")) {
                continue
            }
            val fileText = FileInputStream(file).bufferedReader().use { it.readText() }
            res.add(Json.decodeFromString(fileText))
        }
        return res
    }
}