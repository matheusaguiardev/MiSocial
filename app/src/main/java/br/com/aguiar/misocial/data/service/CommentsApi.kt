package br.com.aguiar.misocial.data.service

import br.com.aguiar.misocial.data.model.CommentsJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CommentsApi {

    @GET("comments")
    fun getCommentsFromService(@Query("postId") postId: Int): Deferred<Response<List<CommentsJson>>>

}