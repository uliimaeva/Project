package tat.neft.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tat.neft.R
import tat.neft.files.MyFile
import tat.neft.files.MyFileLister
import tat.neft.files.MyRecyclerAdapter


class MainActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)


        recyclerView.layoutManager = GridLayoutManager(this, 3)

        val mfl: MyFileLister = MyFileLister()
        val recyclerAdapter = MyRecyclerAdapter(mfl.getDirectoryFilesList())
        recyclerView.adapter = recyclerAdapter


//        val image: ImageButton = findViewById(R.id.i)
//        image.setOnClickListener {
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gismeteo.ru/weather-almetevsk-11940/now/")))
//        }

    }
}