package com.example.binlist.di.components

import com.example.binlist.presentation.viewmodels.SearchViewModel
import dagger.Subcomponent

@Subcomponent
interface SearchComponent {
    fun viewModel(): SearchViewModel
}