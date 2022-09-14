package com.example.firsthomework.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.firsthomework.view_models.NewsState
import com.example.firsthomework.view_models.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val state = viewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        when (state.value) {
            is NewsState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
            is NewsState.Error -> {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                ) {
                    Text(
                        text = (state.value as NewsState.Error).throwable.message.toString(),
                        textAlign = TextAlign.Center
                    )
                    Button(
                        onClick = { viewModel.refresh() },
                    ) {
                        Text("Попробовать ещё раз")
                    }
                }
            }
            is NewsState.Content -> {
                val news = (state.value as NewsState.Content).news
                LazyColumn(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.Center),
                ) {
                    items(news) { item ->
                        Text(
                            text = item.text,
                            textAlign = TextAlign.Center
                        )
                    }
                    item {
                        Button(
                            onClick = { viewModel.refresh() },
                        ) {
                            Text("Загрузить Новые")
                        }
                    }
                }

            }
        }
    }
}