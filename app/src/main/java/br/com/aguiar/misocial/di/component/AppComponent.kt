package br.com.aguiar.misocial.di.component

import br.com.aguiar.misocial.app.MyApp
import br.com.aguiar.misocial.di.modules.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ViewModelModule::class,
    InteractorModule::class,
    RemoteModule::class,
    RepositoryModule::class,
    ActivityModule::class
])
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<MyApp>

}