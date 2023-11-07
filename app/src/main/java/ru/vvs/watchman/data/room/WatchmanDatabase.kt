package ru.vvs.watchman.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.vvs.watchman.data.room.dao.JournalDao
import ru.vvs.watchman.model.Journal

@Database(entities = [Journal::class], version = 1)
abstract class WatchmanDatabase: RoomDatabase() {

    abstract fun getJournalDao(): JournalDao

    companion object {
        @Volatile
        private var database: WatchmanDatabase ?= null

        fun getInstance(context: Context): WatchmanDatabase {
            return  if (database == null) {
                database = Room
                    .databaseBuilder(context, WatchmanDatabase::class.java, "db_terminal")
                    .fallbackToDestructiveMigration()
                    .build()
                database as WatchmanDatabase
            } else {
                database as WatchmanDatabase
            }
        }
    }
}