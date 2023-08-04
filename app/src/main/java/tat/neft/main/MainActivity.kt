package tat.neft.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tat.neft.R
import tat.neft.files.ConfigWorker
import tat.neft.files.MyAdapter
import tat.neft.files.MyFile
import java.io.File


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private var fileArray: ArrayList<MyFile> = ArrayList()
    lateinit var adapter: MyAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        val gridLayoutManager = GridLayoutManager(this, 3)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = gridLayoutManager



        val worker = ConfigWorker(applicationContext)

        for (app in worker.readConfig()) {
            Log.wtf("CONFIG", "aboba " + app.url)
        }

        var config = worker.readConfig()
        adapter = MyAdapter(this, this, config)
        recyclerView.adapter = adapter



        //readFiles()
    }

    fun readFiles() {
        val fileName = "test.txt"
        val lines: List<String> = File(fileName).readLines()
        lines.forEach { line -> Toast.makeText(this, line, Toast.LENGTH_SHORT).show() }
    }


    fun updateAdapterList() {
        adapter.setFilteredList(fileArray)
    }

//        val image: ImageButton = findViewById(R.id.i)
//        image.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gismeteo.ru/weather-almetevsk-11940/now/")))
//        }


}