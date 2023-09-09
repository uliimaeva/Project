package tat.neft.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tat.neft.R
import tat.neft.main.work_with_files.ConfigWorker
import tat.neft.main.adapter.MyAdapter
import tat.neft.main.work_with_files.MyFile


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private var fileArray: ArrayList<MyFile> = ArrayList()
    lateinit var adapter: MyAdapter

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val gridLayoutManager = GridLayoutManager(this, 3)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = gridLayoutManager


        if (!Environment.isExternalStorageManager()) {
            val intent = Intent(
                Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION,
                Uri.parse("package:" + applicationContext.packageName)
            )
            startActivity(intent)
        }

        adapter = MyAdapter(this, this, mutableListOf(MyFile("test", "test", "DrawableApp")))
        recyclerView.adapter = adapter
    }
}