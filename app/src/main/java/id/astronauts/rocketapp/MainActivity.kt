package id.astronauts.rocketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import id.astronauts.rocketapp.ui.theme.RocketAppTheme
import id.astronauts.rocketapp.viewmodel.MainUiState
import id.astronauts.rocketapp.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val newsViewModel: NewsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RocketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val state by newsViewModel.mainUiState.observeAsState(MainUiState.Loading)
                    Greeting(mainUiState = state)
                    newsViewModel.getNews()
                }
            }
        }
    }
}

@Composable
fun Greeting(mainUiState: MainUiState) {
    when (mainUiState) {
        is MainUiState.Loading -> Text("Loading")
        is MainUiState.Fail -> Text("Fail")
        is MainUiState.Success -> Text(mainUiState.news.first())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RocketAppTheme {
        Greeting(MainUiState.Success(listOf("News 1")))
    }
}
