package benhamida.jassem.core.data.mapper

import benhamida.jassem.core.data.model.WeatherCity
import benhamida.jassem.core.data.model.WeatherDTO
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather

fun WeatherDTO.toWeather(city: City): Weather {
    return Weather(
        city.id,
        this.current?.temp,
        this.current?.feels_like,
        this.current?.pressure,
        this.current?.humidity,
        this.current?.wind_speed,
        this.current?.wind_deg,
        this.current?.wind_gust
    )
}

fun Weather.toWeatherCity(): WeatherCity {
    return WeatherCity(
        0,
        this.city_id,
        this.temp,
        this.feels_like,
        this.pressure,
        this.humidity,
        this.wind_speed,
        this.wind_deg,
        this.wind_gust
    )
}

