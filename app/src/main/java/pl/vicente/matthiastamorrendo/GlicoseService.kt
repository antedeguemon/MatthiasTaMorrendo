package pl.vicente.matthiastamorrendo

import retrofit2.Call
import retrofit2.http.GET

interface GlicoseService {
    @GET("list.php")
    fun list() : Call<List<GlicoseEntry>>
}