package com.example.navigationapiapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class BlankFragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment1, container, false)

        val btn_to_frag2 = view.findViewById<Button>(R.id.button_to_frag2)
        btn_to_frag2.setOnClickListener { btn ->
            btn.findNavController().navigate(R.id.action_blankFragment1_to_blankFragment2)
        }

        val btn_to_frag3 = view.findViewById<Button>(R.id.button_to_frag3)
        btn_to_frag3.setOnClickListener { btn ->
            btn.findNavController().navigate(R.id.action_blankFragment1_to_blankFragment3)
        }

        val btn_to_frag4 = view.findViewById<Button>(R.id.button_to_frag4)
        btn_to_frag4.setOnClickListener { btn ->
            btn.findNavController().navigate(R.id.action_blankFragment1_to_blankFragment4)
        }

        return view
    }
}