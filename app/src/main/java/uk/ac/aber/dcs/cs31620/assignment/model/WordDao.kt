package uk.ac.aber.dcs.cs31620.assignment.model

import androidx.lifecycle.LiveData
import androidx.room.*
import java.time.LocalDateTime

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: Word)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateWord(word: Word)

    @Query("DELETE FROM words")
    fun deleteAll()

    @Query("SELECT * FROM words")
    fun getWords(): LiveData<List<Word>>
}
