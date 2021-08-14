package com.example.tesproject

open class Satellite {
    open fun getWeather():Weather{
        return Weather.RAINY
    }
    open fun getWeather(latitude:Double,longitude:Double):Weather{
        return Weather.RAINY
    }
}

class StubSatellite(val anyWhether:Weather):Satellite(){//サテライトを置き換えるスタブ

    override fun getWeather(): Weather {
        return anyWhether
    }

}

open class WeatherFormatter{
    open fun format(weather: Weather):String = "Weather is ${weather}"
}

class SpyWeatherFormatter: WeatherFormatter(){
    var weather: Weather? = null
    var isCalled = false

    override fun format(weather: Weather):String{
        this.weather = weather
        isCalled = true
        return super.format(weather)
    }
}



open class WeatherRecorder{
    //open fun record(weather:Weather){
    open fun record(weather:String){
/*略*/
    }
}

class MockWeatherRecorder: WeatherRecorder(){//引数を返さない依存コンポーネントをモック化
    var weather: Weather? = null
    var isCalled = false

    /*override fun record(weather: Weather){
        this.weather = weather
        isCalled = true*///呼び出しがあった事実を検証のために記録
}


class WeatherForecast(val satellite:Satellite,
                      val recorder: WeatherRecorder,
                      val formatter: WeatherFormatter){//テストケースからモックに差し替えられるようにコンストラ引数でWeatherRecordオブジェクトをとる。

    fun shouldBringUmbrella():Boolean{
        val weather = satellite.getWeather()
        return when (weather){
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather(){
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
        //recorder.record(weather)
            //戻り値を持たないので、外部からは処理の完了が検証できない。
            //メソッドが内部で呼ばれたことを確認して、テスト成功とする。
            //依存コンポーネントであるWeatherRecorderをモックに差し替える。
    }
}



enum class Weather{
    SUNNY, CLOUDY, RAINY
}