package uk.ac.aber.dcs.cs31620.assignment.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import uk.ac.aber.dcs.cs31620.assignment.model.Language
import uk.ac.aber.dcs.cs31620.assignment.model.LanguagesDao

@Database(entities = [Language::class], version = 1)
abstract class LanguageDatabase : RoomDatabase(), RoomDatabaseInterface {

    abstract override fun languagesDao(): LanguagesDao

    override fun closeDb() {
        instance?.close()
        instance = null
    }

    companion object {
        private var instance: LanguageDatabase? = null

        fun getDatabase(context: Context): LanguageDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder<LanguageDatabase>(
                            context.applicationContext,
                            LanguageDatabase::class.java,
                            "language_database"
                        )
                            .addCallback(roomDatabaseCallback(context))
                            .build()
                }
                return instance!!
            }
        }

        private fun roomDatabaseCallback(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                }
            }
        }
    }

}