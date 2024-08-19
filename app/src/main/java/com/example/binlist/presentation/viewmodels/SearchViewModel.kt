package com.example.binlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.R
import com.example.binlist.domain.DbRepository
import com.example.binlist.domain.Repository
import com.example.binlist.domain.models.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository,
    private val dbRepository: DbRepository
) : ViewModel() {

    private val state = MutableStateFlow(SearchState())
    fun observeUi() = state.asStateFlow()

    fun getCard(bin: String) {
        viewModelScope.launch {
            try {
                val card = repository.getCard(bin)
                state.update { it.copy(card = card, error = null) }
                if (card != null) {
                    dbRepository.addNewCard(card, bin.toInt())
                }
            } catch (e: Exception) {
                state.update { it.copy(error = R.string.error_message) }
            }
        }
    }
}

data class SearchState(
    val card: Card? = null,
    val error: Int? = null
)