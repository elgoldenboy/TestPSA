package benhamida.jassem.testpsa.ui.weather_data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather
import benhamida.jassem.core.domain.usecase.GetWeatherInformationUseCase
import benhamida.jassem.core.domain.usecase.UpdateWeatherInformationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherDataViewModel constructor(private val getWeatherInformationUseCase: GetWeatherInformationUseCase,
        private val updateWeatherInformationUseCase: UpdateWeatherInformationUseCase) : ViewModel() {

    lateinit var currentCity: City

    var weatherDetails : MutableLiveData<Weather> = MutableLiveData()

    fun getWeatherInfoFromCache(cityId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            getWeatherInformationUseCase.invoke(cityId)?.let {
                weatherDetails.postValue(it)
            }
        }
    }

    fun updateWeatherInfo(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            updateWeatherInformationUseCase.invoke(city)
            getWeatherInfoFromCache(city.id)
        }
    }
}