package ru.nct.mylovelyplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.nct.mylovelyplace.ui.theme.MyLovelyPlaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val component = getApplicationComponent()
            val placeViewModel: PlaceViewModel = viewModel(factory = component.getViewModelFactory())

            MyLovelyPlaceTheme {

            }
        }
    }
}