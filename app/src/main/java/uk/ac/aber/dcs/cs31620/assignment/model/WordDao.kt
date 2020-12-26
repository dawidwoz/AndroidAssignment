package uk.ac.aber.dcs.cs31620.assignment.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: Word)

    @Query("DELETE FROM words")
    fun deleteAll()

    @Query("SELECT * FROM words")
    fun getWords(): LiveData<List<Word>>
}
