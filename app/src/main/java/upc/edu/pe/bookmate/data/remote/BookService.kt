package upc.edu.pe.bookmate.data.remote

import retrofit2.Response
import retrofit2.http.GET
import upc.edu.pe.bookmate.data.models.BookResponse

interface BookService {
    @GET("books")
    suspend fun getBooks(): Response<List<BookResponse>>
}
