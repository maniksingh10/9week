package com.example.a9week.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.a9week.R
import com.example.a9week.TempSettingsDisplayManager
import com.example.a9week.formatTempDisplay
import com.example.a9week.showTempDialog

class DetailsActivity : AppCompatActivity() {


    private lateinit var tempSettingsDisplayManage: TempSettingsDisplayManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        tempSettingsDisplayManage = TempSettingsDisplayManager(this)
        setTitle(R.string.fore_det_title)


        val temp_tv = findViewById<TextView>(R.id.tv_temp)
        val description_tv = findViewById<TextView>(R.id.tv_description)

        temp_tv.text = formatTempDisplay(
            intent.getFloatExtra("key_temp", 0F),
            tempSettingsDisplayManage.getTempDisplaySetting()
        )
        description_tv.text = intent.getStringExtra("key_description")


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val infaltor: MenuInflater = menuInflater
        infaltor.inflate(R.menu.setting_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.temp_display_settings -> {
                showTempDialog(this, tempSettingsDisplayManage)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
