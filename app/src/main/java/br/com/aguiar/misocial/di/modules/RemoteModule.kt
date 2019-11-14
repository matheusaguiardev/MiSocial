package br.com.aguiar.misocial.di.modules

import br.com.aguiar.misocial.data.service.CommentsApi
import br.com.aguiar.misocial.data.service.PostsApi
import br.com.aguiar.misocial.data.service.UsersApi
import br.com.aguiar.misocial.data.service.Utils
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
val remoteModule = Kodein {
    single { createOkHttpClient() }
    single { createWebService<UsersApi>(get()) }
    single { createWebService<CommentsApi>(get()) }
    single { createWebService<PostsApi>(get()) }
}
*/

@Module
class RemoteModule {

    @Provides
    fun providerUserApi(): UsersApi {
        return createWebService(createOkHttpClient())
    }

    @Provides
    fun providerCommentsApi(): CommentsApi {
        return createWebService(createOkHttpClient())
    }

    @Provides
    fun providerPostsApi(): PostsApi {
        return createWebService(createOkHttpClient())
    }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(Utils.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

