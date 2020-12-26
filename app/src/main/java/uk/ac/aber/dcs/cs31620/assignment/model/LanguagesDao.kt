package uk.ac.aber.dcs.cs31620.assignment.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LanguagesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguage(language: Language)

    @Query("DELETE FROM languages")
    fun deleteAll()

    @Query("SELECT * FROM languages")
    fun getLanguages(): LiveData<List<Language>>
}
