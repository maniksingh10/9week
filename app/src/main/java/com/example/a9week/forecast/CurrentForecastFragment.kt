package com.example.a9week.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a9week.*
import com.example.a9week.details.DetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CurrentForecastFragment : Fragment() {

    private lateinit var tempSettingsDisplayManager: TempSettingsDisplayManager
    private val forecastRepo = ForecastRepo()
    private lateinit var appNavigator: AppNavigator

    override fun onAttach(context: Context) {
        super.onAttach(context)

        appNavigator = context as AppNavigator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tempSettingsDisplayManager = TempSettingsDisplayManager(requireContext())

        val pin = arguments!!.getString(KEY_PIN) ?: ""

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)
        val locationButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        locationButton.setOnClickListener {
            appNavigator.navigateToLocationEntry()
        }

        val forecastListRecycler: RecyclerView = view.findViewById(R.id.forecastlistrecycler)
        forecastListRecycler.layoutManager = LinearLayoutManager(requireContext())
        val dailyForecastAdapter = DailyForecastAdapter(tempSettingsDisplayManager) {
            showDetails(it)
        }
        forecastListRecycler.adapter = dailyForecastAdapter

        val weeklyObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepo.weeklyForecast.observe(this, weeklyObserver)


        forecastRepo.loadForecast(pin)


        return view
    }


    private fun showDetails(f: DailyForecast) {
        val detailsIntent = Intent(requireContext(), DetailsActivity::class.java)
        detailsIntent.putExtra("key_temp", f.currentTemputure)
        detailsIntent.putExtra("key_description", f.description)

        startActivity(detailsIntent)

    }


    companion object {

        const val KEY_PIN = "key_pin"

        fun newInstance(pin: String): CurrentForecastFragment {

            val fragment = CurrentForecastFragment()

            val args = Bundle()
            args.putString(KEY_PIN, "110046")
            fragment.arguments = args

            return fragment

        }
    }

}