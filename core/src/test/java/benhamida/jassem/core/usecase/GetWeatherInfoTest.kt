package benhamida.jassem.core.usecase

import benhamida.jassem.core.domain.model.Weather
import benhamida.jassem.core.domain.repository.WeatherRepository
import benhamida.jassem.core.domain.usecase.GetWeatherInformationUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetWeatherInfoTest {

    @MockK
    lateinit var weatherRepository: WeatherRepository

    @MockK
    lateinit var mockWeather: Weather

    lateinit var getWeatherInformationUseCase: GetWeatherInformationUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getWeatherInformationUseCase = GetWeatherInformationUseCase(weatherRepository)
    }


    @Test
    fun testGetAllTownUseCase() {
        var id : Long = 3
        coEvery {weatherRepository.getWeather(id)} returns mockWeather
        //
        val result = runBlocking {
            getWeatherInformationUseCase.invoke(id)
        }
        //
        coVerify {
            weatherRepository.getWeather(id)
        }
        Assert.assertEquals(
            result,
            mockWeather
        )
    }
}