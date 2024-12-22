package com.example.create_account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.accountcreate.R

class ListAdapter(private val mList: List<ListItem>) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = mList[position]

        holder.imageView.setImageResource(recipe.image)
        holder.textView.text = recipe.title

        holder.itemView.setOnClickListener {
            val recipeId = recipe.id
            Toast.makeText(
                holder.itemView.context,
                "Recipe clicked, ID: $recipeId",
                Toast.LENGTH_SHORT
            ).show()
        }

        holder.likeButton.setOnClickListener {
            val recipeId = recipe.id
            Toast.makeText(holder.itemView.context, "Like button clicked, ID: $recipeId", Toast.LENGTH_SHORT).show()
        }

        holder.shareButton.setOnClickListener {
            val recipeId = recipe.id
            Toast.makeText(holder.itemView.context, "Share button clicked, ID: $recipeId", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImage)
        val textView: TextView = itemView.findViewById(R.id.itemTitle)
        val likeButton: Button = itemView.findViewById(R.id.btnLike)
        val shareButton: Button = itemView.findViewById(R.id.btnShare)
    }
}
