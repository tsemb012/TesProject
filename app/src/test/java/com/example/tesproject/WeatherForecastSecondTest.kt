package com.example.tesproject


import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class WeatherForecastSecondTest {

    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mock(name = "MockSatellite")//スタブ作成
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY)//スタブメソッド作成
            //最新のライブラリでは、wheneverはなくなっているようなので変更後のメソッドを確認する。
        val recorder = WeatherRecorder()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse
    }
}