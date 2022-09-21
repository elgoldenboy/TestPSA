package benhamida.jassem.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City (
    var id: Long = 0,
    var name: String,
    var lat: Double = 0.0,
    var lon: Double = 0.0
): Parcelable