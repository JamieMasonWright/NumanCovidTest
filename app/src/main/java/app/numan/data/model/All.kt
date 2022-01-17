package app.numan.data.model

import com.google.gson.annotations.SerializedName

data class All(

    @SerializedName("confirmed") var confirmed: Int? = null,
    @SerializedName("recovered") var recovered: Int? = null,
    @SerializedName("deaths") var deaths: Int? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("population") var population: Int? = null,
    @SerializedName("sq_km_area") var sqKmArea: Int? = null,
    @SerializedName("life_expectancy") var lifeExpectancy: String? = null,
    @SerializedName("elevation_in_meters") var elevationInMeters: Int? = null,
    @SerializedName("continent") var continent: String? = null,
    @SerializedName("abbreviation") var abbreviation: String? = null,
    @SerializedName("location") var location: String? = null,
    @SerializedName("iso") var iso: Int? = null,
    @SerializedName("capital_city") var capitalCity: String? = null

)