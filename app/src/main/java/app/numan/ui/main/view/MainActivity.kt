package app.numan.ui.main.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.numan.*
import app.numan.data.model.Country
import app.numan.ui.main.adapter.CountriesListAdapter


class MainActivity : AppCompatActivity() {

    private val countriesViewModel: CountriesViewModel by viewModels {
        CountriesViewModelFactory((application as CovidApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val PREFS_NAME = "MyPrefsFile"

        val settings = getSharedPreferences(PREFS_NAME, 0)

         if (settings.getBoolean("my_first_time", true)) {
             val fileName = "countries.txt"
             application.assets.open(fileName).bufferedReader().forEachLine {
                 val country = Country(it.toString(),false)
                 countriesViewModel.insert(country)
             }
             settings.edit().putBoolean("my_first_time", false).apply()
         }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.isLayoutFrozen = true
        val adapter = CountriesListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)




        countriesViewModel.allCountries.observe(owner = this) { countries ->
            countries.let { adapter.submitList(it) }
        }



    }


}
