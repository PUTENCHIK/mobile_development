package com.example.navigationapiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController

class BlankFragment3 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment3, container, false)

        val btn_to_frag5 = view.findViewById<Button>(R.id.button_to_frag5)
        btn_to_frag5.setOnClickListener { btn ->
            btn.findNavController().navigate(R.id.action_blankFragment3_to_blankFragment5)
        }

        val btn_to_frag6 = view.findViewById<Button>(R.id.button_to_frag6)
        btn_to_frag6.setOnClickListener { btn ->
            btn.findNavController().navigate(R.id.action_blankFragment3_to_blankFragment6)
        }

        val btn_back = view.findViewById<Button>(R.id.button_back)
        btn_back.setOnClickListener { btn ->
            btn.findNavController().navigate(R.id.blankFragment1)
        }

        return view
    }
}