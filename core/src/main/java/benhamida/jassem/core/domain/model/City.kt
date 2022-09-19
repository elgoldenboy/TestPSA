package benhamida.jassem.core.domain.model

data class City (
    var id: Long = 0,
    var name: String,
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    var timezone: String? = null,
    var timezone_offset: Long? = null
)