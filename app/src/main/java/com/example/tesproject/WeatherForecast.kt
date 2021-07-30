package com.example.tesproject

open class Satellite {
    open fun getWeather():Weather{
        return Weather.RAINY
    }
}

class StubSatellite(val anyWhether:Weather):Satellite(){//サテライトを置き換えるスタブ

    override fun getWeather(): Weather {
        return anyWhether
    }

}

class WeatherForecast(val satellite:Satellite){

    fun shouldBringUmbrella():Boolean{
        val weather = satellite.getWeather()
        return when (weather){
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }
}

enum class Weather{
    SUNNY, CLOUDY, RAINY
}