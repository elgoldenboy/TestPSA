package benhamida.jassem.testpsa.ui.list_cities

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.usecase.GetAllCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CitiesListViewModel constructor(private val getAllCitiesUseCase: GetAllCitiesUseCase) : ViewModel() {

    var citiesList : MutableLiveData<List<City>?> = MutableLiveData(arrayListOf())

    fun getAllCities() {
        viewModelScope.launch(Dispatchers.IO) {
            citiesList.postValue(getAllCitiesUseCase.invoke())
        }
    }
}