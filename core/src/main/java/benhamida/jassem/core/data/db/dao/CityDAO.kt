package benhamida.jassem.core.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import benhamida.jassem.core.data.db.entity.CityModel

@Dao
interface CityDAO {

    @Insert
    fun insert(cityModel: CityModel)

    @Query("SELECT * FROM citymodel")
    fun getAllCities(): List<CityModel>?

    @Query("SELECT * FROM citymodel WHERE id = :id limit 1")
    fun get(id: Long): CityModel?
}