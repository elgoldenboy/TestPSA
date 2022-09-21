package benhamida.jassem.core.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


annotation class TextPlain
annotation class Json

interface CommonApi {

    // factory methods
    companion object {

        private val gson : Gson by lazy {
            GsonBuilder().setLenient().create()
        }

        fun <T> createBuilder(clazz : Class<T>, url : String): T = Retrofit.Builder()
            // here we set the base url of our API
            .baseUrl(url)
            // add the JSON dependency so we can handle json APIs
            //.addConverterFactory(ApiConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(clazz)

        suspend fun <T : Any> safeApiCall(errorMessage: String, call: suspend () -> Response<T>): ResultApi<T> = try {
            val response : Response<T> = call.invoke()
            when(response.isSuccessful) {
                true -> ResultApi.Success(response.body())
                else -> {
                    showError(response)
                    ResultApi.Error(code = response.code(), exception = IOException(errorMessage))
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            ResultApi.Error(exception = IOException(errorMessage, e))
        }

        protected fun showError(response: Response<*>) {
            val code = response.code()
            val message = response.errorBody()!!.string()
            Log.e("ERROR", "$code - $message")
        }
    }
}


/*
class ApiConverterFactory : Converter.Factory() {

    companion object {
        val moshi = Moshi.Builder()
            .add(Date::class.java, ApiDateJsonAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, *>? =
        bodyConverter(annotations)?.responseBodyConverter(type, annotations, retrofit)

    override fun requestBodyConverter(type: Type, parameterAnnotations: Array<Annotation>, methodAnnotations: Array<Annotation>, retrofit: Retrofit): Converter<*, RequestBody>? =
        (bodyConverter(parameterAnnotations) ?: bodyConverter(methodAnnotations))
            ?.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)

    private fun bodyConverter(annotations: Array<Annotation>): Converter.Factory? {
        return annotations.mapNotNull { annotation ->
            when (annotation) {
                is TextPlain -> ScalarsConverterFactory.create()
                is Json -> MoshiConverterFactory.create(moshi)
                else -> null
            }
        }
            .firstOrNull()
    }
}

class ApiDateJsonAdapter : JsonAdapter<Date?>() {

    private val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())

    @Synchronized
    @Throws(IOException::class)
    override fun fromJson(reader: JsonReader): Date? {
        if (reader.peek() == JsonReader.Token.NULL) {
            return reader.nextNull()
        }
        return sdf.parse(reader.nextString())
    }

    @Synchronized
    @Throws(IOException::class)
    override fun toJson(writer: JsonWriter, value: Date?) {
        value?.let { writer.value(sdf.format(value)) } ?: writer.nullValue()
    }
}*/