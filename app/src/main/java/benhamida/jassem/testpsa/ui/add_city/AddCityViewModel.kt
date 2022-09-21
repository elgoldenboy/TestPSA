package benhamida.jassem.testpsa.ui.add_city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.usecase.AddCityUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddCityViewModel constructor(private val addCityUseCase: AddCityUseCase) : ViewModel() {

    var operationEnd: MutableLiveData<Boolean> = MutableLiveData(false)

    fun addCity(name: String, lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            operationEnd.postValue(false)
            addCityUseCase.invoke(City(name = name, lat = lat, lon = lon))
            operationEnd.postValue(true)
        }
    }
}