package id.astronauts.rocketapp.usecases

import java.util.*

interface UserRepository {

    fun getPreferredDateFormat(): String

    fun getPreferredLocale(): Locale
}
