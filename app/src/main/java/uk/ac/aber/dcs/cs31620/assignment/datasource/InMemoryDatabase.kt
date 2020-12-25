package uk.ac.aber.dcs.cs31620.assignment.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uk.ac.aber.dcs.cs31620.assignment.model.Language
import uk.ac.aber.dcs.cs31620.assignment.model.LanguagesDao

@Database(entities = [Language::class], version = 1)
abstract class InMemoryDatabase : RoomDatabase(), RoomDatabaseInterface {

    abstract override fun languagesDao(): LanguagesDao

    override fun closeDb() {
        instance?.close()
        instance = null
    }

    companion object {
        private var instance: InMemoryDatabase? = null

        fun getDatabase(context: Context): InMemoryDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.inMemoryDatabaseBuilder(
                            context.applicationContext,
                            InMemoryDatabase::class.java
                        ).build()
                }
                return instance!!
            }
        }
    }

}