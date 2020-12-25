package uk.ac.aber.dcs.cs31620.assignment.datasource

import uk.ac.aber.dcs.cs31620.assignment.model.LanguagesDao

interface RoomDatabaseInterface {
    fun languagesDao(): LanguagesDao
    fun closeDb()
}