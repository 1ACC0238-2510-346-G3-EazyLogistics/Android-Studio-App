package upc.edu.pe.bookmate.data.di

import androidx.room.Room
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import upc.edu.pe.bookmate.BookMateApplication
import upc.edu.pe.bookmate.data.local.AppDatabase
import upc.edu.pe.bookmate.data.local.BookDao
import upc.edu.pe.bookmate.data.remote.ApiConstants
import upc.edu.pe.bookmate.data.remote.BookService
import upc.edu.pe.bookmate.data.repository.BookRepository

object DataModule {

    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideBookService(): BookService {
        return provideRetrofit().create(BookService::class.java)
    }

    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            BookMateApplication.instance.applicationContext,
            AppDatabase::class.java,
            "books-db"
        ).build()
    }

    fun provideBookDao(): BookDao {
        return provideDatabase().bookDao()
    }

    fun provideBookRepository(): BookRepository {
        return BookRepository(
            bookService = provideBookService(),
            bookDao = provideBookDao()
        )
    }
}
