package tat.neft.files

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileInputStream
import java.io.OutputStreamWriter

class ConfigWorker(val context: Context) {
    val CONFIG_NAME: String = "config.json"
    val CONFIG_PATH: String = "/storage/emulated/0/Download/" + CONFIG_NAME
    val DEFAULT_CONFIG: MutableList<MyFile> = mutableListOf(MyFile("test.xml", "Test App", "www.google.com"))

    fun configExists(): Boolean {
        return File(CONFIG_PATH).exists()
    }

    fun readConfig(): MutableList<MyFile> {
        if (!configExists()) {
            dumpConfig(DEFAULT_CONFIG)
            return DEFAULT_CONFIG
        }

        val text = FileInputStream(File(CONFIG_PATH)).bufferedReader().use { it.readText() }
        return Json.decodeFromString(text)
    }

    fun dumpConfig(values: MutableList<MyFile>) {
        val json = Json.encodeToString(values)
        File(CONFIG_PATH).writeBytes(json.toByteArray())
    }

    fun optionExists(option: MyFile): Boolean {
        return readConfig().filter { f -> f.url == option.url }.isNotEmpty()
    }

    fun addOption(option: MyFile) {
        val newConfig = readConfig().filter { f -> f.url != option.url }.toMutableList()
        newConfig.add(option)
        dumpConfig(newConfig)
    }

    fun deleteOption(option: MyFile) {
        dumpConfig(readConfig().filter { f -> f.url != option.url }.toMutableList())
    }
}