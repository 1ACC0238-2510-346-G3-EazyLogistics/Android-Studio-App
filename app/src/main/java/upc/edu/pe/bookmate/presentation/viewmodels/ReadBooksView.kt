package upc.edu.pe.bookmate.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import upc.edu.pe.bookmate.domain.models.Book
import upc.edu.pe.bookmate.presentation.viewmodels.ReadBooksViewModel

@Composable
fun ReadBooksView(viewModel: ReadBooksViewModel) {
    val readBooks = viewModel.readBooks.collectAsState()

    viewModel.loadReadBooks()

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(readBooks.value) { book ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Column(Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = book.image,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(book.title, style = MaterialTheme.typography.titleMedium)
                    Text(book.author)
                    Text("Calificación: ${book.rating ?: "-"}")
                    Text("Leído en: ${book.readDate ?: "-"}")
                    Button(
                        onClick = { viewModel.deleteBook(book) },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Eliminar")
                    }
                }
            }
        }
    }
}
