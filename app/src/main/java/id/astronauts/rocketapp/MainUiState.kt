package id.astronauts.rocketapp

sealed class MainUiState {
    object Loading : MainUiState()
    data class Success(val news: List<String>) : MainUiState()
    data class Fail(val error: String) : MainUiState()
}
