package id.astronauts.rocketapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.astronauts.rocketapp.usecases.GetLatestNewsWithAuthorsUseCase
import kotlinx.coroutines.launch

class LatestNewsViewModel(
    private val getLatestNewsWithAuthorsUseCase: GetLatestNewsWithAuthorsUseCase
) : ViewModel() {

    private val _mainUiState = MutableLiveData<MainUiState>()
    val mainUiState: LiveData<MainUiState>
        get() = _mainUiState

    fun getLatestNewsWithAuthor() {
        viewModelScope.launch { getLatestNewsWithAuthorsUseCase() }
    }
}
