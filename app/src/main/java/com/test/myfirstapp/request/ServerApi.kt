package com.test.myfirstapp.request
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.myfirstapp.data.Weather
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//http://api.openweathermap.org/data/2.5/weather?q=Novosibirsk&appid=4429fa1e302622aa5e26f5ac151b8bbb
const val keyApi = "4429fa1e302622aa5e26f5ac151b8bbb"
public interface ServerApi {
@GET("weather")
fun getWeatherResponce(
    @Query("q") cityName :String
): Deferred<Weather>

    companion object {
        operator fun invoke() : ServerApi {
            val requestInterceptor = Interceptor {chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", keyApi)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()
            return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ServerApi::class.java)
        }
    }
}