package id.astronauts.rocketapp

import id.astronauts.rocketapp.usecases.FormatDateUseCase
import id.astronauts.rocketapp.usecases.UserRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class FormatDateUseCaseTest {

    @Test
    fun `success case`() {
        // Given
        val repo = object : UserRepository {
            override fun getPreferredDateFormat(): String {
                return "dd MMM, HH:mm"
            }

            override fun getPreferredLocale(): Locale {
                return Locale("id", "ID")
            }
        }
        val useCase = FormatDateUseCase(repo)
        val independenceDay = LocalDateTime.of(2022, 8, 17, 10, 15, 0)
        val date = Date.from(independenceDay.toInstant(ZoneOffset.of("+07:00")))

        // When
        val actual = useCase(date)

        // Then
        val expected = "17 Agt, 10:15"
        assertEquals(actual, expected)
    }

    @Test
    fun `fail case`() {
        // Given
        val repo = object : UserRepository {
            override fun getPreferredDateFormat(): String {
                return "dd MMM, HH:mm"
            }

            override fun getPreferredLocale(): Locale {
                throw IllegalStateException()
            }
        }

        // When
        val result = runCatching {
            val useCase = FormatDateUseCase(repo)
            useCase(Date(System.currentTimeMillis()))
        }

        // Then
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is IllegalStateException)
    }
}
