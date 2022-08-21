package id.astronauts.rocketapp

import android.app.Application
import id.astronauts.rocketapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class RocketApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RocketApp)
            modules(appModule)
        }
    }
}
