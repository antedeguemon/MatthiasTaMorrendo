package pl.vicente.matthiastamorrendo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
                            .baseUrl("https://vicente.pl/matthias/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()

    fun glicoseService(): GlicoseService {
        return retrofit.create(GlicoseService::class.java)
    }
}