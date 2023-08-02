package tat.neft.files

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import tat.neft.R

class MyRecyclerAdapter(private var files: List<MyFile>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    public var onItemClick: ((MyFile) -> Unit)? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val titleTextView: TextView = itemView.findViewById(R.id.textView_name)
        val singerTextView: TextView = itemView.findViewById(R.id.textView_path)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(files[adapterPosition])
            }
        }

        override fun onClick(p0: View?) {
            Toast.makeText(itemView.context, "${titleTextView.text} pressed!", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.titleTextView.text = files[position].name
        holder.singerTextView.text = files[position].path
    }

    override fun getItemCount(): Int {
        return files.size
    }

    public fun updateDataFiles(newFiles: List<MyFile>) {
        this.files = newFiles
        notifyDataSetChanged()
    }
}