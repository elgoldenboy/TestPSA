package benhamida.jassem.core.data.repository

import benhamida.jassem.core.data.db.dao.CityDAO
import benhamida.jassem.core.data.mapper.toCity
import benhamida.jassem.core.data.mapper.toModel
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.repository.CityRepository

class CityRepositoryImpl(private val cityDAO: CityDAO): CityRepository {

    override suspend fun getCities(): ArrayList<City> {
        var result: ArrayList<City> = arrayListOf()
        cityDAO.getAllCities()?.forEach {
            result.add(it.toCity())
        }
        return result
    }

    override suspend fun addCity(city: City) {
        cityDAO.insert(city.toModel())
    }

}