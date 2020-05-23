package com.example.a9week

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepo {

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast

    fun loadForecast(zipcode: String) {
        val randomValue = List(17) { Random.nextFloat().rem(100)*100 }
        val forecastItems = randomValue.map{
            DailyForecast(it,"party cloudy")
        }
        _weeklyForecast.setValue(forecastItems)
    }



}
