package com.example.create_account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.accountcreate.R

class ListAdapter(
    private var itemList: List<ListItem>,
    private val itemClickListener: (ListItem) -> Unit,
    private val actionClickListener: (ListItem, String) -> Unit
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = itemList[position]

        holder.imageView.setImageResource(recipe.image)
        holder.textView.text = recipe.title

        holder.itemView.setOnClickListener {
            itemClickListener(recipe)
        }

        holder.likeButton.setOnClickListener {
            actionClickListener(recipe, "Like")
        }

        holder.shareButton.setOnClickListener {
            actionClickListener(recipe, "Share")
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newList: List<ListItem>) {
        itemList = newList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
        val textView: TextView = itemView.findViewById(R.id.itemTitle)
        val likeButton: Button = itemView.findViewById(R.id.btnLike)
        val shareButton: Button = itemView.findViewById(R.id.btnShare)
    }
}
