package br.com.aguiar.misocial.di

import br.com.aguiar.misocial.ui.comments.CommentsViewModel
import br.com.aguiar.misocial.ui.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get(), get()) }
    single { CommentsViewModel(get()) }
}