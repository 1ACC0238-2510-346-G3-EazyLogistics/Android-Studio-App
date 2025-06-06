package upc.edu.pe.bookmate.presentation.di

import upc.edu.pe.bookmate.data.di.DataModule
import upc.edu.pe.bookmate.presentation.viewmodels.BookCatalogViewModel
import upc.edu.pe.bookmate.presentation.viewmodels.ReadBooksViewModel

object PresentationModule {

    fun provideBookCatalogViewModel(): BookCatalogViewModel {
        return BookCatalogViewModel(DataModule.provideBookRepository())
    }

    fun provideReadBooksViewModel(): ReadBooksViewModel {
        return ReadBooksViewModel(DataModule.provideBookRepository())
    }
}
