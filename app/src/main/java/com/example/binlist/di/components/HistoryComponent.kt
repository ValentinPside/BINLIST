package com.example.binlist.di.components

import com.example.binlist.presentation.viewmodels.HistoryViewModel
import dagger.Subcomponent

@Subcomponent
interface HistoryComponent {

    fun viewModel(): HistoryViewModel

}