package com.example.binlist.di

import com.example.binlist.presentation.SearchViewModel
import dagger.Subcomponent

@Subcomponent
interface SearchComponent {
    fun viewModel(): SearchViewModel
}