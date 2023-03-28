package com.example.midterm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm.UserApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataLoaderViewModel : ViewModel() {

    private val _usersLiveData: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    val usersLiveData: LiveData<List<User>> = _usersLiveData
    private val networkService by lazy { NetworkService() }

    fun loadUsersList() {
        viewModelScope.launch(Dispatchers.IO) {
            _usersLiveData.postValue(networkService.loadUsersList())
        }
    }


}