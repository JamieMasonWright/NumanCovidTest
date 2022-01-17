package app.numan.data.api

import app.numan.data.model.All
import com.google.gson.annotations.SerializedName

class CovidResponse {
    @SerializedName("All" ) var all : All? = All()
}