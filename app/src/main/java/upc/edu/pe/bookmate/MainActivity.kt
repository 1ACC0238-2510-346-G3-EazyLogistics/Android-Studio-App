package upc.edu.pe.bookmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import upc.edu.pe.bookmate.presentation.navigation.Home
import upc.edu.pe.bookmate.ui.theme.BookmateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookmateTheme {
                Home()
            }
        }
    }
}
