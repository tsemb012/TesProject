package com.example.tesproject

class WeatherForecastSecond(
        val satellite: Satellite,
        val recorder:WeatherRecorder,
        val formatter:WeatherFormatter) {

    fun shouldBringUmbrella():Boolean{
        val weather = satellite.getWeather()
        return when(weather){
            Weather.SUNNY,Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather(){
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }
}