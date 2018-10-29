package `in`.motodoc.dap_test.retrofitService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    fun mServiceAPI(): ServiceInterface? {
        val Base_URL:String = "https://motodoc.in/"
        var retrofit: Retrofit? = null

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Base_URL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit?.create(ServiceInterface::class.java)

    }
}