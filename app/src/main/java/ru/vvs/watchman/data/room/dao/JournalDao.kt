package ru.vvs.watchman.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import ru.vvs.watchman.model.Journal

@Dao
interface JournalDao: BaseDao<Journal> {

    @Query("SELECT * from journal_table")
    suspend fun getAllRecords(): List<Journal>

}