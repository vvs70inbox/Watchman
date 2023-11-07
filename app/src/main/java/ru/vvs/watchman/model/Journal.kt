package ru.vvs.watchman.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "journal_table")
data class Journal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val dateRecord: String,
    val messageRecord: String,
    val typeRecord: String
)
