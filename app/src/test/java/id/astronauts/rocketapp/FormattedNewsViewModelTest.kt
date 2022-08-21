package id.astronauts.rocketapp

import id.astronauts.rocketapp.usecases.FormatDateUseCase
import id.astronauts.rocketapp.usecases.GetFormattedNewsUseCase
import id.astronauts.rocketapp.usecases.NewsRepository
import id.astronauts.rocketapp.usecases.UserRepository
import id.astronauts.rocketapp.viewmodel.FormattedNewsViewModel
import id.astronauts.rocketapp.viewmodel.MainUiState
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [31])
class FormattedNewsViewModelTest {

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
        val formattedNewsViewModel =
            FormattedNewsViewModel(newsRepo, GetFormattedNewsUseCase(newsRepo, formatDateUseCase))

        val listOfStates = mutableListOf<MainUiState>()
        formattedNewsViewModel.mainUiState.observeForever {
            listOfStates.add(it)
        }

        // When
        formattedNewsViewModel.getNews()

        // Then
        assertTrue(listOfStates.size == 2)
        assertTrue(listOfStates[0] == MainUiState.Loading)
        assertTrue(listOfStates[1] == MainUiState.Success(emptyList()))
    }
}
