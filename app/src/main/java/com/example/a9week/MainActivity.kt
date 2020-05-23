package com.example.a9week

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val forecastRepo = ForecastRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button_Show_forecast : Button = findViewById(R.id.button1)

        button_Show_forecast.setOnClickListener {
            forecastRepo.loadForecast("110046")
        }

        val forecastListRecycler: RecyclerView =findViewById(R.id.forecastlistrecycler)
        forecastListRecycler.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter(){
            val msg = getString(R.string.forecast_clicke,it.currentTemputure,it.description)

            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()

        }
        forecastListRecycler.adapter =dailyForecastAdapter

        val weeklyObserver = Observer<List<DailyForecast>>{ forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepo.weeklyForecast.observe(this,weeklyObserver)
    }




}
