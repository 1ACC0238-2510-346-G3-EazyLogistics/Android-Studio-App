package upc.edu.pe.bookmate.data.models

import com.google.gson.annotations.SerializedName
import upc.edu.pe.bookmate.domain.models.Book

data class BookResponse(
    val id: Int,
    val title: String?,
    val author: String?,

    @SerializedName("cover")
    val image: String?,

    @SerializedName("publisher")
    val editorial: String?,

    val year: Int?,

    @SerializedName("category")
    val genre: String?,

    @SerializedName("description")
    val synopsis: String?,

    val rating: Double?
) {
    fun toDomain(): Book {
        return Book(
            id = id,
            title = title.orEmpty(),
            author = author.orEmpty(),
            year = year ?: 0,
            image = image.orEmpty(),
            editorial = editorial.orEmpty(),
            genre = genre.orEmpty(),
            synopsis = synopsis.orEmpty(),
            rating = rating?.toInt(),
            isRead = false,
            readDate = null
        )
    }
}
