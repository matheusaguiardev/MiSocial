package br.com.aguiar.misocial.data.service

import br.com.aguiar.misocial.data.model.PostsJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PostsApi {

    @GET("posts")
    fun getPostsFromService(): Deferred<Response<List<PostsJson>>>

}