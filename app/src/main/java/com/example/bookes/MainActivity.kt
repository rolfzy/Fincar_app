package com.example.bookes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bookes.ui.HomeScreen
import com.example.bookes.ui.theme.BookesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    HomeScreen(onItemClick = {})
                }
            }
        }

    }
}
