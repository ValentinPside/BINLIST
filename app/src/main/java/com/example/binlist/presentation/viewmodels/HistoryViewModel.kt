package com.example.binlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.R
import com.example.binlist.domain.DbRepository
import com.example.binlist.domain.models.RvCard
import com.example.binlist.utils.asRvCardList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val dbRepository: DbRepository
) : ViewModel() {

    private val state = MutableStateFlow(HistoryState())
    fun observeUi() = state.asStateFlow()

    fun getCardList() {
        viewModelScope.launch {
            try {
                var listRv = mutableListOf<RvCard>()
                dbRepository.getCardList().collect { it ->
                    listRv.addAll(it.asRvCardList())
                    state.update { it.copy(list = listRv, error = null) }
                }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }
}

data class HistoryState(
    val list: List<RvCard>? = null,
    val error: Int? = null
)