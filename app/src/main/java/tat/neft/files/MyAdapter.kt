package tat.neft.files

//import com.bumptech.glide.Glide
//import practice.library.retrofit.Common
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tat.neft.R


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
        var url: String = ""
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
        holder.url = fileArray[position].url
    }

    override fun getItemCount(): Int {
        return fileArray.size
    }


    fun setFilteredList(filteredBook: ArrayList<MyFile>) {
        this.fileArray = filteredBook
        notifyDataSetChanged()
    }


}