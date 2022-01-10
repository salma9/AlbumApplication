package com.example.albumapplication.di

import com.albumapplication.data.apiservices.ApiClientModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton
import android.content.Context
import com.albumapplication.data.repository.AlbumModule
import com.example.albumapplication.view.MainActivity


//create the graph of dependencies
@Singleton
@Component(modules = [ApiClientModule::class, AlbumModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }
    // Classes that can be injected by this Component
    //telling Dagger that RegistrationActivity requests injection
    //and that it has to provide the dependencies which are annotated with @Inject
    fun inject(activity: MainActivity)
}