package upc.edu.pe.bookmate.domain.models

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val image: String,
    val editorial: String,
    val genre: String,
    val synopsis: String,
    val isRead: Boolean,
    val rating: Int?,
    val readDate: String?
)
