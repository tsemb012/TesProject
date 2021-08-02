package com.example.tesproject

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class WeatherForecastTest {
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder:MockWeatherRecorder

    @Before
    fun setUp() {
        val satellite = Satellite()
        recorder = MockWeatherRecorder()//WeatherRecorderの代わりにモックを渡す。
        weatherForecast = WeatherForecast(satellite, recorder)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun recordCurrentWeather_assertCalled(){
        weatherForecast.recordCurrentWeather()

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather)
            .isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)

    }


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