package tat.neft.files

import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.annotation.RequiresApi
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.Path

private const val TAG = "VIEWSELECTEDFILE"

class MyFileLister (val path: String = "MyFileStorage") {

    private var status = false
    lateinit var fileTreeWalk: FileTreeWalk
    lateinit var currentDirectory: String
    lateinit var rootStorageDirectory: String

    private val isExternalStorageReadOnly: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)
    }

    private val isExternalStorageAvailable: Boolean get() {
        val extStorageState = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED.equals(extStorageState)
    }

    init {
        Log.i(TAG, "init")

        if (!isExternalStorageReadOnly || isExternalStorageAvailable) {
            rootStorageDirectory = Environment.getExternalStorageDirectory().toString()
            currentDirectory = Environment.getExternalStorageDirectory().toString()
            Log.i(TAG, "pt => ${Environment.getExternalStorageDirectory().toString()}")

            fileTreeWalk = File(Environment.getExternalStorageDirectory().toString()).walk()

            status = true
            Log.i(TAG, "load fileTreeWalk status=" + status.toString())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDirectoryFilesList(selPath: String = ""): List<MyFile> {
        val res = emptyList<MyFile>()
        if (status){
            //Log.i(TAG, "ftw => " + fileTreeWalk.toString())
            //Log.i(TAG, "selPath => " + selPath)
            val tmpListFiles = mutableListOf<MyFile>()

            if (selPath != "") {
                if (selPath == "..") {
                    var i = currentDirectory.length
                    while (i > 0) {
                        i--
                        if (currentDirectory[i] == '/') break
                    }
                    currentDirectory = currentDirectory.substring(0, i)
                }
                else {
                    currentDirectory += "/" + selPath
                }
                Log.i(TAG, "upgate currentDirectory => " + currentDirectory)
                if (currentDirectory != rootStorageDirectory) {
                    val parentDir: MyFile = MyFile("..", currentDirectory, 0, "DIR")
                    tmpListFiles.add(parentDir)
                }
            }

            fileTreeWalk.filter { it.isDirectory }.forEach {
                var onlypath: String = it.path.substring(0, it.path.length - it.name.length - 1)
                var mf = MyFile(it.name, it.path, it.length(), "DIR")
                //Log.i(TAG, "mf => " + mf.toString())
                if (onlypath == currentDirectory)
                    tmpListFiles.add(mf)
            }
            fileTreeWalk.filter { it.isFile }.forEach {
                var onlypath: String = it.path.substring(0, it.path.length - it.name.length - 1)
                var mf = MyFile(it.name, it.path, it.length(), "FILE")
                //Log.i(TAG, "mf => " + mf.toString())
                if (onlypath == currentDirectory)
                    tmpListFiles.add(mf)
            }
            //Log.i(TAG, "onlypath => " + tmpListFiles.toString())
            return tmpListFiles.toList()
        }
        return res
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFilesList(): List<MyFile> {
        val res = emptyList<MyFile>()
        if (status){
           //Log.i(TAG, "ftw => " + fileTreeWalk.toString())
            val tmpListFiles = mutableListOf<MyFile>()
            fileTreeWalk.filter { it.isFile }.forEach {
                var mf = MyFile(it.name, it.path, it.length(), "FILE")
                //Log.i(TAG, "mf => " + mf.toString())
                tmpListFiles.add(mf)
            }
            //Log.i(TAG, "tmpListFiles => " + tmpListFiles.toString())
            return tmpListFiles.toList()
        }
        return res
    }
}