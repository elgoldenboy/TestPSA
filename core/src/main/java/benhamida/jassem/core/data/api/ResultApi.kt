package benhamida.jassem.core.data.api

sealed class ResultApi<out T: Any> {
    data class Success<out T : Any>(val data: T?) : ResultApi<T>()
    data class Error<out T : Any>(val code : Int = 0, val exception: Exception) : ResultApi<T>()
}