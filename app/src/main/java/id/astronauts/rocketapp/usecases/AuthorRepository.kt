package id.astronauts.rocketapp.usecases

interface AuthorRepository {
    fun getAuthor(authorId: String): String
}
