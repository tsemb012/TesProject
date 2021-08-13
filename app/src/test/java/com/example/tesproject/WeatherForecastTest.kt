package com.example.tesproject

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast
    //lateinit var recorder:MockWeatherRecorder
    lateinit var formatter:SpyWeatherFormatter

    @Before
    fun setUp() {
        val satellite = Satellite()
        //recorder = MockWeatherRecorder()//WeatherRecorderの代わりにモックを渡す。
        val recorder = WeatherRecorder()
        formatter = SpyWeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled(){
        weatherForecast.recordCurrentWeather()

        val isCalled =  formatter.isCalled
        assertThat(isCalled).isTrue()

        val weather = formatter.weather
        assertThat(weather)
            .isIn(Weather.SUNNY,Weather.CLOUDY,Weather.RAINY)
    }

   /* @Test
    fun recordCurrentWeather_assertCalled(){
        weatherForecast.recordCurrentWeather()

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)

    }*/



    /*
    @Test
    @Ignore
    fun shouldBringUmbrella_givenSunny_returnFalse() {

        val satellite = StubSatellite(Weather.SUNNY)
        weatherForecast = WeatherForecast(satellite)

        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse

    }
    */
}