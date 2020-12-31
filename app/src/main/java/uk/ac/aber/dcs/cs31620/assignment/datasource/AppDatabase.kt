package uk.ac.aber.dcs.cs31620.assignment.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import uk.ac.aber.dcs.cs31620.assignment.model.*

@Database(entities = [Language::class, Word::class], version = 1)
abstract class AppDatabase : RoomDatabase(), RoomDatabaseInterface {

    abstract override fun languagesDao(): LanguageDao

    abstract override fun wordDao(): WordDao

    override fun closeDb() {
        instance?.close()
        instance = null
    }

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder<AppDatabase>(
                            context.applicationContext,
                            AppDatabase::class.java,
                            Common.DatabaseName.value
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
