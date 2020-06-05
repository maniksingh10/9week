package com.example.a9week.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.a9week.R


class LocationFragment : Fragment() {





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)

        val button_Show_forecast: Button = view.findViewById(R.id.button1)

        button_Show_forecast.setOnClickListener {

            findNavController().navigateUp()

        }





        return view
    }


}