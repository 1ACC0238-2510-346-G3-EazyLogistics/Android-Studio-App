package upc.edu.pe.bookmate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import upc.edu.pe.bookmate.data.models.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
