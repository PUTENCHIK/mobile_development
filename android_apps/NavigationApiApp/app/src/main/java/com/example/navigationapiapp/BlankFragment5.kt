package com.example.navigationapiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class BlankFragment5 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment5, container, false)

        val btn_back = view.findViewById<Button>(R.id.button_back)
        btn_back.setOnClickListener { btn ->
            btn.findNavController().popBackStack()
        }

        return view
    }
}