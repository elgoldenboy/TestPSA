package benhamida.jassem.core.data.api

import android.util.Log
import benhamida.jassem.core.data.Constants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object ApiClient {

    private val TAG = ApiClient::class.java.simpleName

    private val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }

    private val httpClient : OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }

    private val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun createApiService() = retrofit.create(WeatherApi::class.java)

    suspend fun <T : Any> safeApiCall(errorMessage: String, call: suspend () -> Response<T>): ResultApi<T> = try {
        val response : Response<T> = call.invoke()
        when(response.isSuccessful) {
            true -> ResultApi.Success(response.body())
            else -> {
                Log.e(TAG, "Error code: ${response.code()}, error message: ${response.errorBody().toString()}")
                ResultApi.Error(code = response.code(), exception = IOException(errorMessage))
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
        ResultApi.Error(exception = IOException(errorMessage, e))
    }
}
