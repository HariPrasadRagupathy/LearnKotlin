package com.hp.learnkotlin.ui.pagger.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.hp.learnkotlin.ui.pagger.local.UserEntity
import com.hp.learnkotlin.ui.pagger.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    pager: Pager<Int, UserEntity>
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    var searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    var isSearching = _isSearching.asStateFlow()



    val userPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toUser() }
        }.cachedIn(viewModelScope)

    fun onSearchTextChange(text : String){
        _searchText.value = text
    }
}