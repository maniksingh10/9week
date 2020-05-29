package com.example.a9week

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun formatTempDisplay(temp: Float, tempDisplaySetting: TempDisplaySetting): String {
    return when (tempDisplaySetting) {
        TempDisplaySetting.Fahrenheit -> String.format("%.2f °F", temp)
        TempDisplaySetting.Celsius -> {
            val temp = (temp - 32f) * (5f / 9f)
            String.format("%.2f °C", temp)
        }
    }
}

fun showTempDialog(context: Context, tempSettingsDisplayManager: TempSettingsDisplayManager) {

    val dialogBuilderM = AlertDialog.Builder(context)
        .setTitle("Choose Units")
        .setMessage("Which Temperature Unit to Display in")
        .setPositiveButton("°F") { _, _ ->
            tempSettingsDisplayManager.updateSetting(TempDisplaySetting.Fahrenheit)
        }
        .setNeutralButton("°C") { _, _ ->
            tempSettingsDisplayManager.updateSetting(TempDisplaySetting.Celsius)

        }
        .setOnDismissListener {
            Toast.makeText(context, "Restart App", Toast.LENGTH_SHORT).show()
        }
    dialogBuilderM.show()
}