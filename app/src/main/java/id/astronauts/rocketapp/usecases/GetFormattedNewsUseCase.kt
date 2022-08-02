package id.astronauts.rocketapp.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class GetFormattedNewsUseCase(
    private val newsRepository: NewsRepository,
    private val formatDateUseCase: FormatDateUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): Result<List<String>> = withContext(dispatcher) {
        runCatching {
            val news = newsRepository.fetchLatestNews()
            formatDateUseCase(Date(System.currentTimeMillis()))
            news
        }
    }
}
