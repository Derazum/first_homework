package com.example.firsthomework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.firsthomework.components.NewsScreen
import com.example.firsthomework.services.providers.local.LocalProvider
import com.example.firsthomework.services.providers.remote.RemoteDataProvider
import com.example.firsthomework.services.repositories.NewsRepository
import com.example.firsthomework.ui.theme.FirstHomeworkTheme
import com.example.firsthomework.view_models.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val newsViewModel = NewsViewModel(
            repository = NewsRepository(
                remoteDataProvider = RemoteDataProvider(),
                localProvider = LocalProvider(
                    context = applicationContext
                ),
            )
        )

        setContent {
            FirstHomeworkTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NewsScreen(
                        newsViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    CircularProgressIndicator()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FirstHomeworkTheme {
        Greeting()
    }
}