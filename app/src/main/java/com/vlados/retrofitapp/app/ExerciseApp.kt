package com.vlados.retrofitapp.app

import android.app.Application
import com.vlados.retrofitapp.di.AppComponent
import com.vlados.retrofitapp.di.DaggerAppComponent


class ExerciseApp: Application() {

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}