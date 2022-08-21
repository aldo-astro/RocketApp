package id.astronauts.rocketapp.usecases

import id.astronauts.rocketapp.data.NewsService

class RemoteNewsRepository(newsService: NewsService) : NewsRepository {
    override fun fetchLatestNews(): List<String> {
        return emptyList()
    }
}
