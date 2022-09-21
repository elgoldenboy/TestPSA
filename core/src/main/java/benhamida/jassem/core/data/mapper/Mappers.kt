package benhamida.jassem.core.data.mapper

import benhamida.jassem.core.data.db.entity.CityModel
import benhamida.jassem.core.data.db.entity.WeatherModel
import benhamida.jassem.core.data.model.WeatherDTO
import benhamida.jassem.core.data.model.WeatherData
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather

fun WeatherModel.toWeather(): Weather {
    return Weather(
        city_id,
        main,
        description,
        icon,
        temp,
        feels_like,
        pressure,
        humidity,
        wind_speed,
        wind_deg,
        wind_gust
    )
}

fun WeatherDTO.toWeather(city: City): Weather {
    val weatherList = current?.weather
    var weatherData: WeatherData? = null
    if(weatherList?.isNotEmpty() == true) {
        weatherData = weatherList[0]
    }
    return Weather(
        city.id,
        weatherData?.main,
        weatherData?.description,
        weatherData?.icon,
        this.current?.temp,
        this.current?.feels_like,
        this.current?.pressure,
        this.current?.humidity,
        this.current?.wind_speed,
        this.current?.wind_deg,
        this.current?.wind_gust
    )
}

fun Weather.toModel(): WeatherModel {
    return WeatherModel(
        0,
        this.city_id,
        this.main,
        this.description,
        this.icon,
        this.temp,
        this.feels_like,
        this.pressure,
        this.humidity,
        this.wind_speed,
        this.wind_deg,
        this.wind_gust
    )
}

fun CityModel.toCity(): City {
    return City(id, name, lat, lon)
}

fun City.toModel(): CityModel {
    return CityModel(id, name, lat, lon)
}
