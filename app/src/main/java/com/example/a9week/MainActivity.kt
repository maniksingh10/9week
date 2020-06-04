package com.example.a9week

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.a9week.forecast.CurrentForecastFragment
import com.example.a9week.location.LocationFragment


class MainActivity : AppCompatActivity(), AppNavigator {

    private lateinit var tempSettingsDisplayManager: TempSettingsDisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempSettingsDisplayManager = TempSettingsDisplayManager(this)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, LocationFragment())
            .commit()

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


    override fun navigateToCurrentForecast(pin: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CurrentForecastFragment.newInstance("110046"))
            .commit()
    }

    override fun navigateToLocationEntry() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LocationFragment()).commit()
    }

}
