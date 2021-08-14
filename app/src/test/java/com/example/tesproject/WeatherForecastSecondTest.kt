package com.example.tesproject


import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class WeatherForecastSecondTest {

    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mock(name = "MockSatellite")//スタブ作成
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY) //スタブメソッド作成

        //-----引数マッチャー：　引数の値に応じてスタブの戻り値を変更。
        whenever(satellite.getWeather(any(), any())).thenReturn(Weather.CLOUDY)//引数をワイルドカードでマッチさせ、デフォルトの戻り値としてCloudyを返す
        whenever(satellite.getWeather(eq(37.580006), eq(-122.345106))).thenReturn(Weather.SUNNY)//当該座標の時はSUNNY
        whenever(satellite.getWeather(eq(37.792872), eq(-122.396915))).thenReturn(Weather.RAINY)//当該座標の時はRAINY



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