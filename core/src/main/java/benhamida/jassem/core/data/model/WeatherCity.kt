package benhamida.jassem.core.data.model

data class WeatherCity (
    var id: Long? = 0,
    var city_id: Long? = 0,
    var temp: Double? = null,
    var feels_like: Double? = null,
    var pressure: Double? = null,
    var humidity: Double? = null,
    var wind_speed: Double? = null,
    var wind_deg: Double? = null,
    var wind_gust: Double? = null
)