package app.numan.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService {
    @GET("cases?")
    fun getDetails(@Query("country") country : String) : Call<CovidResponse>
}
