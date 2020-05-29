package com.example.a9week

import android.content.Context


enum class TempDisplaySetting {
    Fahrenheit, Celsius
}

class TempSettingsDisplayManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)


    fun updateSetting(set: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display", set.name).commit()
    }


    fun getTempDisplaySetting(): TempDisplaySetting {
        val settingValue =
            preferences.getString("key_temp_display", TempDisplaySetting.Fahrenheit.name)
                ?: TempDisplaySetting.Fahrenheit.name

        return TempDisplaySetting.valueOf(settingValue)
    }
}