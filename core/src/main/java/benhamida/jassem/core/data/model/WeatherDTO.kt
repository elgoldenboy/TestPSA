package benhamida.jassem.core.data.model

data class WeatherDTO (
    var id: Long? = 0,
    var name: String? = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    var timezone: String? = null,
    var timezone_offset: Long? = null,
    var current: Current? = null
)

class Current (
    var dt: Long? = null,
    var sunrise: Double? = null,
    var sunset: Double? = null,
    var temp: Double? = null,
    var feels_like: Double? = null,
    var pressure: Double? = null,
    var humidity: Double? = null,
    var dew_point: Double? = null,
    var uvi: Double? = null,
    var clouds: Double? = null,
    var visibility: Double? = null,
    var wind_speed: Double? = null,
    var wind_deg: Double? = null,
    var wind_gust: Double? = null,
    var weather: ArrayList<WeatherData>? = null,
    var pop: Double? = null,
)

class WeatherData(
    var id: Long? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null
)