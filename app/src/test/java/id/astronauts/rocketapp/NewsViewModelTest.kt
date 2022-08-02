package id.astronauts.rocketapp

import id.astronauts.rocketapp.usecases.FormatDateUseCase
import id.astronauts.rocketapp.usecases.GetFormattedNewsUseCase
import id.astronauts.rocketapp.usecases.NewsRepository
import id.astronauts.rocketapp.usecases.UserRepository
import id.astronauts.rocketapp.viewmodel.MainUiState
import id.astronauts.rocketapp.viewmodel.NewsViewModel
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class NewsViewModelTest {

    @Test
    fun `assert getNews state is correct`() {
        // Given
        val newsRepo = object : NewsRepository {
            override fun fetchLatestNews(): List<String> {
                return emptyList()
            }
        }
        val userRepo = object : UserRepository {
            override fun getPreferredDateFormat(): String {
                return "dd MMM, HH:mm"
            }

            override fun getPreferredLocale(): Locale {
                return Locale("id", "ID")
            }
        }
        val formatDateUseCase = FormatDateUseCase(userRepo)
        val newsViewModel =
            NewsViewModel(newsRepo, GetFormattedNewsUseCase(newsRepo, formatDateUseCase))

        val listOfStates = mutableListOf<MainUiState>()
        newsViewModel.mainUiState.observeForever {
            listOfStates.add(it)
        }

        // When
        newsViewModel.getNews()

        // Then
        assertTrue(listOfStates.size == 2)
        assertTrue(listOfStates[0] == MainUiState.Loading)
        assertTrue(listOfStates[1] == MainUiState.Success(emptyList()))
    }
}
