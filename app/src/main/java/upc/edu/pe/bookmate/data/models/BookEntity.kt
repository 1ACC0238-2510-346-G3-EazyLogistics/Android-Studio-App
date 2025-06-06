package upc.edu.pe.bookmate.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val author: String,
    val year: Int,
    val image: String,
    val genre: String,
    val synopsis: String,
    val editorial: String,
    val rating: Int?,
    val readDate: String
)