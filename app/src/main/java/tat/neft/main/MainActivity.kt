package tat.neft.main
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
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