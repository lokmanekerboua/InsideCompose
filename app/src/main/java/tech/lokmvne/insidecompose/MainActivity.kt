package tech.lokmvne.insidecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.flow.MutableStateFlow
import tech.lokmvne.insidecompose.ui.theme.InsideComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InsideComposeTheme {
                val x = MutableStateFlow(0f)
            }
        }
    }
}
