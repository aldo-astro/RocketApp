package id.astronauts.rocketapp.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class GetLatestNewsWithAuthorsUseCase(
    private val newsRepository: NewsRepository,
    private val authorsRepository: AuthorRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(): List<ArticleWithAuthor> = withContext(dispatcher) {
        val news = newsRepository.fetchLatestNews()
        val result: MutableList<ArticleWithAuthor> = mutableListOf()
        for (article in news) {
            val author = async {
                authorsRepository.getAuthor(article)
            }
            result.add(ArticleWithAuthor(article, author.await()))
        }
        result
    }
}
