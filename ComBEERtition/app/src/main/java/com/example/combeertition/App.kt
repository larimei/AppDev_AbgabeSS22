package com.example.combeertition

import android.app.Application
import androidx.room.Room
import com.example.combeertition.data.database.AppDatabase
import kotlinx.coroutines.runBlocking
import java.util.prefs.Preferences

/**
 * Main entry point into the application process.
 * Registered in the AndroidManifest.xml file.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        database = Room
            .databaseBuilder(this, AppDatabase::class.java, "app")
            .apply {
                if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
            }
            .build()

        /*
         In a real app we should never use runBlocking {}. Especially not on app start up.
         However, we would need to refactor the ProductsRepository to use Flow. Therefore, we accept this hack for now. After all, it is
         just a demo app.
         The real solution would be to launch a coroutine in the app scope:
         private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
         scope.launch {  }
         */
    }

    companion object {
        /** Singleton [UserSettingsRepository] instance. */
        lateinit var database: AppDatabase
    }
}
