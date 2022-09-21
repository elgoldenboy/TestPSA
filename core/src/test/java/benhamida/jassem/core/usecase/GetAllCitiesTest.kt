package benhamida.jassem.core.usecase

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.repository.CityRepository
import benhamida.jassem.core.domain.usecase.GetAllCitiesUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetAllCitiesTest {

    @MockK
    lateinit var cityRepository: CityRepository

    @MockK
    lateinit var city: City

    lateinit var getAllCitiesUseCase: GetAllCitiesUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getAllCitiesUseCase = GetAllCitiesUseCase(cityRepository)
    }


    @Test
    fun testGetAllCitiesUseCase() {
        val cities = arrayListOf(city, city)
        coEvery {cityRepository.getCities()} returns cities
        //
        val result = runBlocking {
            getAllCitiesUseCase.invoke()
        }
        //
        coVerify {
            cityRepository.getCities()
        }
        Assert.assertEquals(
            result,
            cities
        )
    }
}