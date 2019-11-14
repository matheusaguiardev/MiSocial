package br.com.aguiar.misocial.di.modules

import br.com.aguiar.misocial.domain.comment.CommentsInteractor
import br.com.aguiar.misocial.domain.comment.CommentsRepository
import br.com.aguiar.misocial.domain.post.PostsInteractor
import br.com.aguiar.misocial.domain.post.PostsRepository
import br.com.aguiar.misocial.domain.user.UserInteractor
import br.com.aguiar.misocial.domain.user.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/*
val interactorModule = module {
    single { UserInteractor(get()) }
    single { CommentsInteractor(get()) }
    single { PostsInteractor(get()) }
} */

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun providerUserInteractor(repository: UsersRepository) = UserInteractor(repository)

    @Provides
    @Singleton
    fun providerCommentsInteractor(repository: CommentsRepository) = CommentsInteractor(repository)

    @Provides
    @Singleton
    fun providerPostsInteractor(repository: PostsRepository) = PostsInteractor(repository)

}