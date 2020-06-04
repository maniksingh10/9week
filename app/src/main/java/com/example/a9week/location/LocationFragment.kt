package com.example.a9week.location

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.a9week.AppNavigator
import com.example.a9week.R


class LocationFragment : Fragment() {

    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appNavigator = context as AppNavigator
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)

        val button_Show_forecast: Button = view.findViewById(R.id.button1)

        button_Show_forecast.setOnClickListener {

            appNavigator.navigateToCurrentForecast("110046")

        }





        return view
    }


}