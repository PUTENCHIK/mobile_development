package com.example.tabsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val data: List<BookData>)
    : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_number = itemView.findViewById<TextView>(R.id.book_number)
        val tv_name = itemView.findViewById<TextView>(R.id.book_name)
        val tv_author = itemView.findViewById<TextView>(R.id.book_author)
        val tv_year = itemView.findViewById<TextView>(R.id.book_year)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.book_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = data[position]

        holder.tv_number.text = "${position+1})"
        holder.tv_name.text = "\"${itemData.name}\""
        holder.tv_author.text = itemData.author
        holder.tv_year.text = "${itemData.year} год"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}