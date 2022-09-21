package benhamida.jassem.testpsa.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import benhamida.jassem.core.data.api.ApiClient
import benhamida.jassem.core.data.db.AppDatabase
import benhamida.jassem.core.data.repository.CityRepositoryImpl
import benhamida.jassem.core.data.repository.WeatherRepositoryImpl
import benhamida.jassem.core.domain.repository.CityRepository
import benhamida.jassem.core.domain.repository.WeatherRepository
import benhamida.jassem.core.domain.usecase.AddCityUseCase
import benhamida.jassem.core.domain.usecase.GetAllCitiesUseCase
import benhamida.jassem.core.domain.usecase.GetWeatherInformationUseCase
import benhamida.jassem.core.domain.usecase.UpdateWeatherInformationUseCase
import benhamida.jassem.testpsa.ui.add_city.AddCityViewModel
import benhamida.jassem.testpsa.ui.list_cities.CitiesListViewModel
import benhamida.jassem.testpsa.ui.weather_data.WeatherDataViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val cityRepository: CityRepository
    private val weatherRepository: WeatherRepository
    private val addCityUseCase: AddCityUseCase
    private val getAllCitiesUseCase: GetAllCitiesUseCase
    private val updateWeatherInformationUseCase: UpdateWeatherInformationUseCase
    private val getWeatherInformationUseCase: GetWeatherInformationUseCase


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesListViewModel::class.java)) {
            return CitiesListViewModel(getAllCitiesUseCase) as T
        }
        if (modelClass.isAssignableFrom(AddCityViewModel::class.java)) {
            return AddCityViewModel(addCityUseCase) as T
        }
        if (modelClass.isAssignableFrom(WeatherDataViewModel::class.java)) {
            return WeatherDataViewModel(getWeatherInformationUseCase, updateWeatherInformationUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        private var factory: ViewModelProvider.Factory? = null
        fun getInstance(context: Context): ViewModelProvider.Factory {
            if (factory == null) {
                synchronized(ViewModelProvider.Factory::class.java) {
                    if (factory == null) {
                        factory = ViewModelFactory(context)
                    }
                }
            }
            return factory!!
        }
    }

    init {
        val database: AppDatabase = AppDatabase.getInstance(context)
        val weatherApi = ApiClient.createApiService()
        cityRepository = CityRepositoryImpl(database.cityDAO())
        weatherRepository = WeatherRepositoryImpl(weatherApi, database.weatherDAO())
        addCityUseCase = AddCityUseCase(cityRepository)
        getAllCitiesUseCase = GetAllCitiesUseCase(cityRepository)
        getWeatherInformationUseCase = GetWeatherInformationUseCase(weatherRepository)
        updateWeatherInformationUseCase = UpdateWeatherInformationUseCase(weatherRepository)
    }
}