package tat.neft.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import tat.neft.R
import tat.neft.files.MyFile
import tat.neft.files.MyFileLister
import tat.neft.files.MyRecyclerAdapter


class MainActivity : AppCompatActivity() {

    public var onItemClick: ((MyFile) -> Unit)? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        var search: String
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerSearch)
        //val selectFile: TextView = findViewById(R.id.selectedFile)
        //var curSelFile: MyFile = MyFile("file not select", "", 0, "FILE")

        recyclerView.layoutManager = LinearLayoutManager(this)

        val mfl: MyFileLister = MyFileLister()
        val recyclerAdapter = MyRecyclerAdapter(mfl.getDirectoryFilesList())
        recyclerView.adapter = recyclerAdapter
        val searchField: TextInputLayout = findViewById(R.id.search_TIL)
        val searchText: TextInputEditText = findViewById(R.id.search_TIET)
        searchField.setEndIconOnClickListener {
            Toast.makeText(this, "searsching....", Toast.LENGTH_SHORT).show()
            search = searchText.text.toString()
        }

        val image: ImageButton = findViewById(R.id.i)
        image.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gismeteo.ru/weather-almetevsk-11940/now/")))
        }


//        recyclerAdapter.onItemClick = { myFile ->
//            // Log.i(TAG, "pressed ${myFile.name}")
//            if (myFile.type == "FILE") {
//                selectFile.text = myFile.name
//                curSelFile = myFile
//            } else {
//                recyclerAdapter.updateDataFiles(mfl.getDirectoryFilesList(myFile.name))
//            }
//        }
//
//        selectFile.setOnClickListener {
//            if (curSelFile.name != "file not select") {
//                var fileInputStream = FileInputStream(curSelFile.path)
//                val bytesArr: ByteArray = ByteArray(20Ы0)
//                val sz = fileInputStream.read(bytesArr)
//                //Log.i(TAG, "sz => ${sz}")
//                Toast.makeText(this, String(bytesArr), Toast.LENGTH_SHORT).show()
//                fileInputStream.close()
//
//            }
//        }
    }
}