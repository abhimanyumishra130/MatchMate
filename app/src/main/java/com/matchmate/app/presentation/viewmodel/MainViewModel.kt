package com.matchmate.app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.usecase.DeletePersonUseCase
import com.matchmate.app.domain.usecase.FetchMoreUseCase
import com.matchmate.app.domain.usecase.FetchPersonUseCase
import com.matchmate.app.domain.usecase.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchPersonUseCase: FetchPersonUseCase,
    private val getDataUseCase: GetDataUseCase,
    private val fetchMoreUseCase: FetchMoreUseCase,
    private val deletePersonUseCase: DeletePersonUseCase
): ViewModel() {

    val data: StateFlow<List<Person>> = getDataUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    init {
        fetchPersons()
    }

    fun fetchPersons() {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("TAG", "fetchPersons: ")
            val result = fetchPersonUseCase()
            Log.d("TAG", "fetchPersons: $result")
        }
    }



    fun fetchMorePerson() {
        CoroutineScope(Dispatchers.IO).launch {
            val moreData = fetchMoreUseCase()
            Log.d("TAG", "fetchPersons: $moreData")
        }
    }

    fun deletePerson(person: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            deletePersonUseCase(person)
            Log.d("TAG", "deletePerson: ")
        }
    }


}