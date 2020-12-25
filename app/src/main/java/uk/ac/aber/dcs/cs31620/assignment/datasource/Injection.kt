package uk.ac.aber.dcs.cs31620.assignment.datasource

import android.content.Context

object Injection {
    fun getDatabase(context: Context): RoomDatabaseInterface =
        InMemoryDatabase.getDatabase(context)!!
}