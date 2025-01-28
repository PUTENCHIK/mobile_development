package com.example.recyclerviewapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv = itemView.findViewById<TextView>(R.id.inner_text)

    fun bindTo(color: Int) {
        tv.setBackgroundColor(color)
        tv.text = itemView.context.getString(R.string.template, color)
    }
}