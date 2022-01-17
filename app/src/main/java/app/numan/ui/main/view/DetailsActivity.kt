package app.numan.ui.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.numan.*
import app.numan.data.model.Country
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import app.numan.data.api.CovidResponse
import app.numan.data.api.CovidService
import app.numan.data.repository.CountriesRepository
import app.numan.data.room.CountriesRoomDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailsActivity : AppCompatActivity() {

    private val countriesViewModel: CountriesViewModel by viewModels {
        CountriesViewModelFactory((application as CovidApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val country = intent.getStringExtra("Country")
        var countryIsFav = intent.getBooleanExtra("CountryIsFav", false)

        val txtCountry: TextView = findViewById(R.id.country);
        val txtPopulation: TextView = findViewById(R.id.population);
        val txtAdministered: TextView = findViewById(R.id.administered);
        val txtDeaths: TextView = findViewById(R.id.deaths);
        val txtRecovered: TextView = findViewById(R.id.recovered);

        val progressBar: ProgressBar = findViewById(R.id.progressBar);

        val btnSave: Button = findViewById(R.id.btnSave);

        if (countryIsFav) {
            btnSave.text = "Mark UnFavorite"
        } else {
            btnSave.text = "Mark Favorite"
        }

        val btnBack: Button = findViewById(R.id.btnBack);

        btnSave.setOnClickListener(View.OnClickListener {

            val database = CountriesRoomDatabase.getDatabase(this)
            val repository = CountriesRepository(database.countriesDao())

            if (countryIsFav) {

                val tempC = Country(country!!, false)
                tempC.isFav = false
                countryIsFav = false
                btnSave.text = "Mark Favorite"
                Thread {
                    //Do your database´s operations here
                    repository.update(tempC)
                }.start()

            } else {
                val tempC = Country(country!!, false)
                tempC.isFav = true
                countryIsFav = true
                btnSave.text = "Mark UnFavorite"
                Thread {
                    //Do your database´s operations here
                    repository.update(tempC)
                }.start()
            }


        })

        btnBack.setOnClickListener {
            finish();
        }


        val baseUrl = "https://covid-api.mmediagroup.fr/v1/"

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CovidService::class.java)

        val call = service.getDetails(country!!)

        call.enqueue(object : Callback<CovidResponse> {
            override fun onResponse(call: Call<CovidResponse>, response: Response<CovidResponse>) {
                Log.d("MainActivity HOLA", "1")
                if (response.code() == 200) {
                    Log.d("MainActivity HOLA", "2")


                    txtCountry.text = response.body()?.all?.country.toString();
                    txtPopulation.text = response.body()?.all?.population.toString();
                    txtAdministered.text = response.body()?.all?.confirmed.toString();
                    txtDeaths.text = response.body()?.all?.deaths.toString();
                    txtRecovered.text = response.body()?.all?.recovered.toString();

                    progressBar.isVisible = false

                    if (txtPopulation.text == "0") {
                        Toast.makeText(applicationContext, "No Data Found", Toast.LENGTH_LONG)
                            .show()
                    }


                } else {

                    txtCountry.text = country
                    txtPopulation.text = "0";
                    txtAdministered.text = "0";
                    txtDeaths.text = "0";
                    txtRecovered.text = "0";

                    progressBar.isVisible = false
                    Toast.makeText(applicationContext, "No Data Found", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<CovidResponse>, t: Throwable) {
                txtCountry.text = country
                txtPopulation.text = "0";
                txtAdministered.text = "0";
                txtDeaths.text = "0";
                txtRecovered.text = "0";

                progressBar.isVisible = false
                Toast.makeText(
                    applicationContext,
                    "There was an error fetching data",
                    Toast.LENGTH_LONG
                ).show()
            }
        })


    }


}
