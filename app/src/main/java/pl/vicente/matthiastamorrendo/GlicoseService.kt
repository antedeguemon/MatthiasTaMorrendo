package pl.vicente.matthiastamorrendo

import retrofit2.http.GET

interface GlicoseService {
    @GET("matthias_history.php")
    fun list()
}