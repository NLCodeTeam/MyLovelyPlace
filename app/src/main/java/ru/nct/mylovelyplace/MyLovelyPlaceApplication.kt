package ru.nct.mylovelyplace

import android.app.Application
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.FirebaseApp
import ru.nct.mylovelyplace.di.ApplicationComponent
import ru.nct.mylovelyplace.di.DaggerApplicationComponent

class MyLovelyPlaceApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        FirebaseApp.initializeApp(this)
        super.onCreate()
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    Log.d("RECOMPOSITION_TAG", "getApplicationComponent")
    return (LocalContext.current.applicationContext as MyLovelyPlaceApplication).component
}