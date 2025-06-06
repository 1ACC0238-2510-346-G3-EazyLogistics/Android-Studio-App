package upc.edu.pe.bookmate.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import upc.edu.pe.bookmate.domain.models.Book
import upc.edu.pe.bookmate.presentation.viewmodels.ReadBooksViewModel
import java.time.LocalDate

@Composable
fun BookDetailView(
    bookId: Int?,
    navController: NavController,
    viewModel: ReadBooksViewModel
) {
    val book by produceState<Book?>(initialValue = null, bookId) {
        value = bookId?.let { viewModel.getBookById(it) }
    }

    if (book == null) {
        Text(
            text = "Error: libro no encontrado",
            modifier = Modifier.padding(16.dp)
        )
        return
    }

    var rating by remember { mutableStateOf(book?.rating ?: 3) }

    book?.let { safeBook ->
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = safeBook.image,
                contentDescription = "Portada de ${safeBook.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(safeBook.title, style = MaterialTheme.typography.titleLarge)
            Text("Autor: ${safeBook.author}")
            Text("Editorial: ${safeBook.editorial}")
            Text("Género: ${safeBook.genre}")
            Text("Año: ${safeBook.year}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Sinopsis: ${safeBook.synopsis}")

            Spacer(modifier = Modifier.height(12.dp))
            Text("Calificación:")

            Slider(
                value = rating.toFloat(),
                onValueChange = { rating = it.toInt() },
                valueRange = 1f..5f,
                steps = 3
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.markBookAsRead(
                        safeBook.copy(
                            isRead = true,
                            rating = rating,
                            readDate = LocalDate.now().toString()
                        )
                    )
                    navController.popBackStack()
                }
            ) {
                Text("Marcar como leído")
            }
        }
    }
}
