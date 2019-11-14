package br.com.aguiar.misocial.di.modules

import br.com.aguiar.misocial.ui.comments.CommentsActivity
import br.com.aguiar.misocial.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector
    fun homeActivityInject() :  HomeActivity

    @ContributesAndroidInjector
    fun commentsActivityInject() :  CommentsActivity

}