package upc.edu.pe.bookmate.presentation.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import upc.edu.pe.bookmate.domain.models.Book
import upc.edu.pe.bookmate.presentation.viewmodels.BookCatalogViewModel

@Composable
fun BookCatalogView(viewModel: BookCatalogViewModel, navController: NavHostController) {
    val books = viewModel.books.collectAsState()

    viewModel.fetchBooks()

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(books.value) { book ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        navController.navigate("book_detail/${book.id}")
                    }
            ) {
                Row(modifier = Modifier.padding(8.dp)) {
                    AsyncImage(
                        model = book.image,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(book.title, style = MaterialTheme.typography.titleMedium)
                        Text(book.author)
                        Text("Año: ${book.year}")
                    }
                }
            }
        }
    }
}
