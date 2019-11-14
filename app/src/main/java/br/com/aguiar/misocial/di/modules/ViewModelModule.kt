package br.com.aguiar.misocial.di.modules

import br.com.aguiar.misocial.domain.comment.CommentsInteractor
import br.com.aguiar.misocial.domain.post.PostsInteractor
import br.com.aguiar.misocial.domain.user.UserInteractor
import br.com.aguiar.misocial.ui.comments.CommentsViewModel
import br.com.aguiar.misocial.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/*val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { CommentsViewModel(get()) }
}*/

@Module
class ViewModelModule {

    @Provides
    fun providerHomeViewModel(
        postsInteractor: PostsInteractor,
        userInteractor: UserInteractor
    ) = HomeViewModel(postsInteractor, userInteractor)

    @Provides
    fun providercommentsViewModel(interactor: CommentsInteractor) = CommentsViewModel(interactor)

}
