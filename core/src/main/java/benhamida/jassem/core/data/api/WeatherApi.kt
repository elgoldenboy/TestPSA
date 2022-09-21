package benhamida.jassem.core.data.api

import benhamida.jassem.core.data.model.WeatherDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/onecall?")
    suspend fun getWeatherInfo(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appid") appId: String, @Query("exclude") exclude: String, @Query("units") units: String): Response<WeatherDTO>
}