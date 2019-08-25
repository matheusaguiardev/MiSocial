package br.com.aguiar.misocial.di

import br.com.aguiar.misocial.domain.comment.CommentsInteractor
import br.com.aguiar.misocial.domain.post.PostsInteractor
import br.com.aguiar.misocial.domain.user.UserInteractor
import org.koin.dsl.module

val interactorModule = module {
    single { UserInteractor(get()) }
    single { CommentsInteractor(get()) }
    single { PostsInteractor(get()) }
}