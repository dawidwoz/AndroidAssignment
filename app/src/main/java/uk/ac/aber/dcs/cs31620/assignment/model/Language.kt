package uk.ac.aber.dcs.cs31620.assignment.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "languages")
data class Language(
    @PrimaryKey
    @NonNull
    var langauge: String = "",
    @NonNull
    var value: String = ""
) { }
