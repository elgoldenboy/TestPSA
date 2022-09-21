package benhamida.jassem.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CityModel (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var lat: Double = 0.0,
    var lon: Double = 0.0
)