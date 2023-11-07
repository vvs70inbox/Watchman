package ru.vvs.watchman.data

import ru.vvs.watchman.data.room.dao.JournalDao
import ru.vvs.watchman.model.Journal

class JournalRepository(private val journalDao: JournalDao) {

    suspend fun getRecords(newList: Boolean): List<Journal> {
        return journalDao.getAllRecords()
    }

    suspend fun setRecord(journal: Journal) {
        journalDao.insert(journal)
    }
}