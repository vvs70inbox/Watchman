package ru.vvs.watchman.data

import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.vvs.watchman.data.room.dao.JournalDao
import ru.vvs.watchman.model.Journal

class JournalRepository(private val journalDao: JournalDao) {

    suspend fun getRecords(newList: Boolean): List<Journal> {
        return journalDao.getAllRecords()
    }

    suspend fun setRecord(journal: Journal) {
        journalDao.insert(journal)
    }

    suspend fun clearTable() {
        journalDao.clearTable()
    }
}