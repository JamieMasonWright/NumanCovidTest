package app.numan

import android.app.Application
import app.numan.data.repository.CountriesRepository
import app.numan.data.room.CountriesRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CovidApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CountriesRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CountriesRepository(database.countriesDao()) }
}
