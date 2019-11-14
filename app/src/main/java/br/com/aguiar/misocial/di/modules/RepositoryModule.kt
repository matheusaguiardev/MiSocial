package br.com.aguiar.misocial.di.modules

import br.com.aguiar.misocial.data.repository.CommentsRepositoryImp
import br.com.aguiar.misocial.data.repository.PostsRepositoryImp
import br.com.aguiar.misocial.data.repository.UsersRepositoryImp
import br.com.aguiar.misocial.data.service.CommentsApi
import br.com.aguiar.misocial.data.service.PostsApi
import br.com.aguiar.misocial.data.service.UsersApi
import br.com.aguiar.misocial.domain.comment.CommentsRepository
import br.com.aguiar.misocial.domain.post.PostsRepository
import br.com.aguiar.misocial.domain.user.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.Multibinds
import javax.inject.Inject

/*
val repositoryModule = module {
    single<UsersRepository> { UsersRepositoryImp(get()) }
    single<CommentsRepository> { CommentsRepositoryImp(get()) }
    single<PostsRepository> { PostsRepositoryImp(get()) }
}
*/
@Module
class RepositoryModule {

    @Provides
    fun providerUserRepository(api: UsersApi): UsersRepository {
        return UsersRepositoryImp(api)
    }

    @Provides
    fun providerCommentsRepository(api: CommentsApi): CommentsRepository {
        return CommentsRepositoryImp(api)
    }

    @Provides
    fun providerPostsRepository(api: PostsApi): PostsRepository {
        return PostsRepositoryImp(api)
    }

}