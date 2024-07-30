package com.example.binlist.di

import com.example.binlist.presentation.HistoryViewModel
import dagger.Subcomponent

@Subcomponent
interface HistoryComponent {

    fun viewModel(): HistoryViewModel

}