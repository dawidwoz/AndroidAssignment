package uk.ac.aber.dcs.cs31620.assignment.datasource

import uk.ac.aber.dcs.cs31620.assignment.model.LanguageDao
import uk.ac.aber.dcs.cs31620.assignment.model.WordDao

interface RoomDatabaseInterface {
    fun languagesDao(): LanguageDao
    fun wordDao(): WordDao
    fun closeDb()
}
