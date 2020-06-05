package com.example.a9week

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var tempSettingsDisplayManager: TempSettingsDisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempSettingsDisplayManager = TempSettingsDisplayManager(this)


        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setTitle(R.string.app_name)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(
            navController
        )

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
