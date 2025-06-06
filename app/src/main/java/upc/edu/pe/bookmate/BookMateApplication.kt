package upc.edu.pe.bookmate

import android.app.Application

class BookMateApplication : Application() {
    companion object {
        lateinit var instance: BookMateApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
