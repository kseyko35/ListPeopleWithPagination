package com.kseyko.listpeoplewithpagination.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kseyko.listpeoplewithpagination.enums.ErrorTypes
import com.kseyko.listpeoplewithpagination.repo.PersonRepository
import com.kseyko.listpeoplewithpagination.source.FetchCompletionHandler
import com.kseyko.listpeoplewithpagination.source.Person
import com.kseyko.listpeoplewithpagination.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(private val repository: PersonRepository) :
    BaseViewModel() {
    private val _personList: MutableLiveData<List<Person>?> = MutableLiveData()
    val personList: LiveData<List<Person>?> = _personList
    private val _isProgressVisible: MutableLiveData<Boolean> = MutableLiveData()
    val isProgressVisible: LiveData<Boolean> = _isProgressVisible
    private val _errors: MutableLiveData<ErrorTypes> = MutableLiveData()
    val errors: LiveData<ErrorTypes> = _errors
    private var paginationValue: String? = null

    private var handler: FetchCompletionHandler = { fetchResponse, fetchError ->
        if (fetchResponse != null) {
            paginationValue = fetchResponse.next

            if (fetchResponse.people.isEmpty()) {
                _errors.postValue(ErrorTypes.NO_DATA)
            } else if (personList.value.isNullOrEmpty()) {
                _isProgressVisible.value = true
                _personList.postValue(fetchResponse.people.groupBy { it.id }.entries.map { it.value.last() }
                    .sortedBy { it.id })
            } else {
                val newPersonList = personList.value?.plus(fetchResponse.people)
                    ?.groupBy { it.id }?.entries?.map { it.value.last() }?.sortedBy { it.id }
                if ((newPersonList?.size ?: 0) != personList.value!!.size) {
                    _personList.postValue(newPersonList!!)
                } else {
                    _errors.postValue(ErrorTypes.NO_NEW_ITEM)
                }
            }
        }
        if (fetchError != null) {
            when (fetchError.errorTypes) {
                ErrorTypes.INTERNAL_SERVER_ERROR -> _errors.postValue(ErrorTypes.INTERNAL_SERVER_ERROR)
                ErrorTypes.PARAMETER_ERROR -> _errors.postValue(ErrorTypes.PARAMETER_ERROR)
                else -> _errors.postValue(ErrorTypes.NO_DATA)
            }
        }
        _isProgressVisible.value = false
    }

    init {
        fetchPersonList()
    }

    private fun fetchPersonList() {

        repository.fetchPersonList(paginationValue, handler)
    }

    fun loadNextPage() {
        fetchPersonList()
    }

    fun refreshPage() {
        paginationValue = null
        _personList.value = null
        fetchPersonList()
    }
}