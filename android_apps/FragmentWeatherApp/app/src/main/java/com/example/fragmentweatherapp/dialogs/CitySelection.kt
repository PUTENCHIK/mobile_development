package com.example.fragmentweatherapp.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.fragmentweatherapp.MainActivity
import com.example.fragmentweatherapp.R

class CitySelection(private var choice: Int)
    : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cities = resources.getStringArray(R.array.cities)

        return activity?.let {
            AlertDialog.Builder(it)
                .setSingleChoiceItems(cities, choice)
                    {_, which -> choice = which}
                .setNeutralButton(resources.getString(R.string.btn_choose_city_neutral_button))
                    { dialog, _ -> dialog.dismiss() }
                .setPositiveButton(resources.getString(R.string.btn_choose_city_positive_button))
                    {_, _ -> (context as MainActivity).updateCity(choice)}
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}