package upc.edu.pe.bookmate.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import upc.edu.pe.bookmate.data.repository.BookRepository
import upc.edu.pe.bookmate.domain.models.Book

class ReadBooksViewModel(private val repository: BookRepository) : ViewModel() {

    private val _readBooks = MutableStateFlow<List<Book>>(emptyList())
    val readBooks: StateFlow<List<Book>> = _readBooks

    fun loadReadBooks() {
        viewModelScope.launch {
            _readBooks.value = repository.getReadBooks()
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            repository.deleteReadBook(book)
            loadReadBooks()
        }
    }

    fun markBookAsRead(book: Book) {
        viewModelScope.launch {
            repository.markAsRead(book)
            loadReadBooks()
        }
    }

    fun getBookById(id: Int): Book? {
        val readBook = readBooks.value.find { it.id == id }
        if (readBook != null) return readBook
        return runBlocking {
            repository.fetchBooks().find { it.id == id }
        }
    }

}
