package benhamida.jassem.core.domain.model

data class Weather (
    var city_id: Long = 0,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null,
    var temp: Double? = null,
    var feels_like: Double? = null,
    var pressure: Double? = null,
    var humidity: Double? = null,
    var wind_speed: Double? = null,
    var wind_deg: Double? = null,
    var wind_gust: Double? = null
)