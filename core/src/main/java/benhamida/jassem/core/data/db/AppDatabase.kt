package benhamida.jassem.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import benhamida.jassem.core.data.db.dao.CityDAO
import benhamida.jassem.core.data.db.dao.WeatherDAO
import benhamida.jassem.core.data.db.entity.CityModel
import benhamida.jassem.core.data.db.entity.WeatherModel

@Database(entities = [CityModel::class, WeatherModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    //----- DAO -----
    abstract fun cityDAO() : CityDAO
    abstract fun weatherDAO() : WeatherDAO

    companion object {
        //----- SINGLETON -----
        @Volatile
        private var INSTANCE: AppDatabase? = null

        //----- INSTANCE -----
        fun getInstance(context: Context): AppDatabase {
            if(INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if(INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "psa_weather_app_test.db")
                                .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}