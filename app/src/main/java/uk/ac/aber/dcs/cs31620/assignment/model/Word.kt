package uk.ac.aber.dcs.cs31620.assignment.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0,
    @NonNull
    var orginal: String = "",
    @NonNull
    var translation: String = "",
    @NonNull
    var failed: Int = 0
) { }
