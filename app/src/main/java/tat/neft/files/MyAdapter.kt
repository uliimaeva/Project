package tat.neft.files

import android.app.Activity
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import practice.library.retrofit.Common
import tat.neft.R

class MyAdapter(
    private val activity: Activity,
    private var fileArray: ArrayList<MyFile>,
) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var listener: (() -> Unit)? = null
    fun setListener(listener: (() -> Unit)?) {
        this.listener = listener
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name: TextView = itemView.findViewById(R.id.text)
        val icon: TextView = itemView.findViewById(R.id.icon)
        var url: String = ""
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.icon.text = fileArray[position].text
        holder.name.text = fileArray[position].text
        holder.url = fileArray[position].text
    }

    override fun getItemCount(): Int {
        return fileArray.size
    }


    fun setFilteredList(filteredBook: ArrayList<MyFile>) {
        this.fileArray = filteredBook
        notifyDataSetChanged()
    }


}