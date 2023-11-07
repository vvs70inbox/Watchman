package ru.vvs.watchman.screens.startfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.vvs.watchman.data.JournalRepository
import ru.vvs.watchman.data.room.WatchmanDatabase
import ru.vvs.watchman.model.Journal

class StartViewModel(application: Application): AndroidViewModel(application) {

    private val repository: JournalRepository

    private var _myList: MutableLiveData<List<Journal>> = MutableLiveData()
    val myList: LiveData<List<Journal>> = _myList

    var journalItem: MutableLiveData<Journal> = MutableLiveData()

    init {
        val journalDao = WatchmanDatabase.getInstance(application).getJournalDao()
        repository = JournalRepository(journalDao)
    }

    fun getRecords(newList: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            _myList.postValue(repository.getRecords(newList))
        }
    }

    fun setRecord(journal: Journal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setRecord(journal)
            getRecords(false)
        }
    }

}