package tat.neft.main.adapter

//import com.bumptech.glide.Glide
//import practice.library.retrofit.Common
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dalvik.system.DexClassLoader
import tat.neft.R
import tat.neft.main.work_with_files.MyFile


class MyAdapter(
    private val activity: Activity,
    private val context: Context,
    private var fileArray: MutableList<MyFile>,
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var listener: (() -> Unit)? = null
    fun setListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = itemView.findViewById(R.id.text)
        val icon: ImageView = itemView.findViewById(R.id.icon)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val drawable: Drawable = context.resources.getDrawable(
            context.resources.getIdentifier(
                fileArray[position].icon,
                "drawable",
                context.packageName
            )
        )
        holder.icon.setImageDrawable(drawable)
        holder.name.text = fileArray[position].text

        holder.mainLayout.setOnClickListener {
            var loader = DexClassLoader("/storage/emulated/0/Download/classes.jar", activity.filesDir.absolutePath, null, activity.classLoader)
            var cl = loader.loadClass("com.tatneft.exampleexternalapp.ExampleApp")
            // АЛЬТЕРНАТИВНЫЙ ВАРИАНТ С ЗАГРУЗКОЙ ИЗ ЛОКАЛЬНЫХ РЕСУРСОВ
            //val url = fileArray[position].url
            //var cl = activity.classLoader.loadClass("tat.neft.plugins." + url).getDeclaredConstructor().newInstance()
            context.startActivity(Intent(context, cl::class.java))
        }
    }

    override fun getItemCount(): Int {
        return fileArray.size
    }

}