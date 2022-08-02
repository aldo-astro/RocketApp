package id.astronauts.rocketapp.usecases

interface NewsRepository {
    fun fetchLatestNews(): List<String>
}
