package upc.edu.pe.bookmate.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import upc.edu.pe.bookmate.data.repository.BookRepository
import upc.edu.pe.bookmate.domain.models.Book

class BookCatalogViewModel(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    fun fetchBooks() {
        viewModelScope.launch {
            _books.value = repository.fetchBooks()
        }
    }
}
