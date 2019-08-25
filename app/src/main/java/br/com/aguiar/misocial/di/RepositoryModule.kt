package br.com.aguiar.misocial.di

import br.com.aguiar.misocial.data.repository.CommentsRepositoryImp
import br.com.aguiar.misocial.data.repository.PostsRepositoryImp
import br.com.aguiar.misocial.data.repository.UsersRepositoryImp
import br.com.aguiar.misocial.domain.comment.CommentsRepository
import br.com.aguiar.misocial.domain.post.PostsRepository
import br.com.aguiar.misocial.domain.user.UsersRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImp(get()) }
    single<CommentsRepository> { CommentsRepositoryImp(get()) }
    single<PostsRepository> { PostsRepositoryImp(get()) }
}