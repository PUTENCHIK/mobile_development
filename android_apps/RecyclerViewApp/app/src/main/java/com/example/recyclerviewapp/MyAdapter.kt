package com.example.recyclerviewapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

internal class MyAdapter(private val inflater: LayoutInflater) :
    ListAdapter<Int, MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row: View = inflater.inflate(R.layout.item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Int> =
            object : DiffUtil.ItemCallback<Int>() {
                override fun areItemsTheSame(oldColor: Int, newColor: Int): Boolean {
                    return oldColor == newColor
                }

                override fun areContentsTheSame(oldColor: Int, newColor: Int): Boolean {
                    return areItemsTheSame(oldColor, newColor)
                }
            }
    }
}