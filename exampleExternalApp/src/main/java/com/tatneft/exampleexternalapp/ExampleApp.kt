package com.tatneft.exampleexternalapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
class ExampleApp : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }

    @Composable
    private fun App() {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("App!")
                TimerComponent()
            }
        }
    }

    @Composable
    fun TimerComponent() {
        var secondsPassed by remember {
            mutableStateOf(0)
        }
        LaunchedEffect(key1 = secondsPassed) {
            delay(1000L)
            secondsPassed += 1
        }
        Text("${secondsPassed} сек. прошло с момента запуска приложения!")
    }

    @Preview
    @Composable
    private fun AppPreview() {
        App()
    }
}