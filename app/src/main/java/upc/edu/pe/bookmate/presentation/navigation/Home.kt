package upc.edu.pe.bookmate.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.CollectionsBookmark
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import upc.edu.pe.bookmate.presentation.di.PresentationModule
import upc.edu.pe.bookmate.presentation.views.*

@Composable
fun Home() {
    val navController = rememberNavController()

    val tabs = listOf(
        TabItem("catalog", "Catálogo", Icons.AutoMirrored.Filled.MenuBook),
        TabItem("read", "Mi Biblioteca", Icons.Default.CollectionsBookmark)
    )

    var selectedTab by remember { mutableStateOf(0) }

    val catalogViewModel = PresentationModule.provideBookCatalogViewModel()
    val readBooksViewModel = PresentationModule.provideReadBooksViewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) },
                        selected = selectedTab == index,
                        onClick = {
                            selectedTab = index
                            navController.navigate(item.route) {
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "catalog",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("catalog") {
                BookCatalogView(catalogViewModel, navController)
            }
            composable("read") {
                ReadBooksView(readBooksViewModel)
            }
            composable(
                "book_detail/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.IntType })
            ) { backStackEntry ->
                val bookId = backStackEntry.arguments?.getInt("bookId")
                BookDetailView(bookId, navController, readBooksViewModel)
            }
        }

    }
}

data class TabItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)
