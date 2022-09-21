package benhamida.jassem.core.data.db.dao

import androidx.room.*
import benhamida.jassem.core.data.db.entity.WeatherModel

@Dao
interface WeatherDAO {

    @Insert
    fun insert(weatherModel: WeatherModel)

    @Update
    fun update(weatherModel: WeatherModel)

    @Query("SELECT * FROM weathermodel WHERE city_id = :cityId limit 1")
    fun getWeatherForCity(cityId: Long): WeatherModel?
}