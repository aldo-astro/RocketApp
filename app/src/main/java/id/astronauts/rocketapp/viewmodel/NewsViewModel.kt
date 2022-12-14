package id.astronauts.rocketapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.astronauts.rocketapp.usecases.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository,
) : ViewModel() {

    private val _mainUiState = MutableLiveData<MainUiState>()
    val mainUiState: LiveData<MainUiState>
        get() = _mainUiState

    val mainUiEffect = MutableLiveData<MainUiEffect>()

    fun getNews() {
        _mainUiState.value = MainUiState.Loading
        viewModelScope.launch {
            try {
                val news = newsRepository.fetchLatestNews()
                _mainUiState.value = MainUiState.Success(news)
            } catch (e: Exception) {
                _mainUiState.value = MainUiState.Fail(e.localizedMessage)
            }
        }
    }
}
