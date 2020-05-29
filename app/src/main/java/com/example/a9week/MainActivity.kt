package com.example.a9week

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a9week.details.DetailsActivity


class MainActivity : AppCompatActivity() {

    private val forecastRepo = ForecastRepo()
    private lateinit var tempSettingsDisplayManager: TempSettingsDisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempSettingsDisplayManager = TempSettingsDisplayManager(this)

        val button_Show_forecast: Button = findViewById(R.id.button1)

        button_Show_forecast.setOnClickListener {
            forecastRepo.loadForecast("110046")

        }

        val forecastListRecycler: RecyclerView = findViewById(R.id.forecastlistrecycler)
        forecastListRecycler.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter(tempSettingsDisplayManager) {
            showDetails(it)
        }
        forecastListRecycler.adapter = dailyForecastAdapter

        val weeklyObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepo.weeklyForecast.observe(this, weeklyObserver)
    }


    private fun showDetails(f: DailyForecast) {
        val detailsIntent = Intent(this, DetailsActivity::class.java)
        detailsIntent.putExtra("key_temp", f.currentTemputure)
        detailsIntent.putExtra("key_description", f.description)

        startActivity(detailsIntent)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infaltor: MenuInflater = menuInflater
        infaltor.inflate(R.menu.setting_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.temp_display_settings -> {
                showTempDialog(this, tempSettingsDisplayManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
