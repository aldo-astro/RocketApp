package id.astronauts.rocketapp.di

import id.astronauts.rocketapp.data.NewsService
import id.astronauts.rocketapp.usecases.NewsRepository
import id.astronauts.rocketapp.usecases.RemoteNewsRepository
import id.astronauts.rocketapp.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.example.com")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
    single<NewsService> {
        val retrofit: Retrofit = get()
        retrofit.create(NewsService::class.java)
    }
    single<NewsRepository> { RemoteNewsRepository(get()) }
    viewModel {
        NewsViewModel(get())
    }
}
