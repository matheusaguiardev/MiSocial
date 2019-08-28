package br.com.aguiar.misocial.di

import br.com.aguiar.misocial.ui.comments.CommentsViewModel
import br.com.aguiar.misocial.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CommentsViewModel(get()) }
}