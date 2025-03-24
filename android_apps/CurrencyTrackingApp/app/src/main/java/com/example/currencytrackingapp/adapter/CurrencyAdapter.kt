package com.example.currencytrackingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.currencytrackingapp.databinding.CurrencyLayoutBinding
import java.time.format.DateTimeFormatter
import java.util.Locale

class CurrencyAdapter(
    context: Context,
    resource: Int,
    objects: List<CurrencyObject>
) : ArrayAdapter<CurrencyObject>(context, resource, objects) {

    lateinit var binding: CurrencyLayoutBinding

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val items: List<CurrencyObject> = objects
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern(
        "d MMMM, HH:mm:ss",
        Locale("ru")
    )

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listItem: CurrencyObject? = getItem(position)
        binding = when (convertView) {
            null -> CurrencyLayoutBinding.inflate(inflater, parent, false)
            else -> convertView.tag as CurrencyLayoutBinding
        }

        listItem?.let {
            binding.currencyIcon.setImageResource(it.iconSrc)
            binding.currencyName.text = "${it.label}, ${it.name}"
            binding.currencyQuotation.text = "${it.quotation}"
            binding.currencyUpdated.text = it.updated.format(formatter)
        }

        binding.root.tag = binding
        return binding.root
    }

    override fun getCount(): Int {
        return items.size
    }
}