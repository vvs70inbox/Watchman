package ru.vvs.watchman.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.vvs.watchman.model.Journal

@Dao
interface JournalDao: BaseDao<Journal> {

    @Query("SELECT * FROM journal_table ORDER BY dateRecord DESC")
    suspend fun getAllRecords(): List<Journal>

    @Query("DELETE FROM journal_table")
    suspend fun clearTable()

}