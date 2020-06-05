package com.example.a9week.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a9week.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class WeeklyForecastFragment : Fragment() {

    private lateinit var tempSettingsDisplayManager: TempSettingsDisplayManager
    private val forecastRepo = ForecastRepo()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tempSettingsDisplayManager = TempSettingsDisplayManager(requireContext())

        val pin = arguments?.getString(KEY_PIN) ?: ""

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weekly_forecast, container, false)
        val locationButton = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        locationButton.setOnClickListener {
            showEntry()
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
        forecastRepo.weeklyForecast.observe(requireActivity(), weeklyObserver)


        forecastRepo.loadForecast(pin)


        return view
    }

    private fun showEntry() {
        val action =
            CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationFragment()
        findNavController().navigate(action)
    }


    private fun showDetails(f: DailyForecast) {
        val action =
            CurrentForecastFragmentDirections.actionCurrentForecastFragmentToDetailsFragment(
                f.currentTemputure,
                f.description
            )
        findNavController().navigate(action)

    }


    companion object {

        const val KEY_PIN = "key_pin"

        fun newInstance(pin: String): WeeklyForecastFragment {

            val fragment = WeeklyForecastFragment()

            val args = Bundle()
            args.putString(KEY_PIN, "110046")
            fragment.arguments = args

            return fragment

        }
    }

}