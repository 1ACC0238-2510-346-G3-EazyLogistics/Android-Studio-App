package upc.edu.pe.bookmate.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import upc.edu.pe.bookmate.data.local.BookDao
import upc.edu.pe.bookmate.data.models.BookEntity
import upc.edu.pe.bookmate.data.remote.BookService
import upc.edu.pe.bookmate.domain.models.Book

class BookRepository(
    private val bookService: BookService,
    private val bookDao: BookDao
) {

    suspend fun fetchBooks(): List<Book> = withContext(Dispatchers.IO) {
        val response = bookService.getBooks()
        if (response.isSuccessful) {
            response.body()?.map { it.toDomain() } ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getReadBooks(): List<Book> = withContext(Dispatchers.IO) {
        return@withContext bookDao.getAll().map {
            Book(
                id = it.id,
                title = it.title,
                author = it.author,
                year = it.year,
                image = it.image,
                genre = it.genre,
                synopsis = it.synopsis,
                editorial = it.editorial,
                isRead = true,
                rating = it.rating,
                readDate = it.readDate
            )
        }
    }

    suspend fun markAsRead(book: Book) = withContext(Dispatchers.IO) {
        val entity = BookEntity(
            id = book.id,
            title = book.title,
            author = book.author,
            year = book.year,
            image = book.image,
            genre = book.genre,
            synopsis = book.synopsis,
            editorial = book.editorial,
            rating = book.rating,
            readDate = book.readDate ?: ""
        )
        bookDao.insert(entity)
    }

    suspend fun deleteReadBook(book: Book) = withContext(Dispatchers.IO) {
        bookDao.delete(
            BookEntity(
                id = book.id,
                title = book.title,
                author = book.author,
                year = book.year,
                image = book.image,
                genre = book.genre,
                synopsis = book.synopsis,
                editorial = book.editorial,
                rating = book.rating,
                readDate = book.readDate ?: ""
            )
        )
    }
}
