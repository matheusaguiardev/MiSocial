package br.com.aguiar.misocial.data.service

import br.com.aguiar.misocial.data.model.CommentsJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CommentsApi {

    @GET("comments")
    fun getCommentsFromService(): Deferred<Response<List<CommentsJson>>>

}