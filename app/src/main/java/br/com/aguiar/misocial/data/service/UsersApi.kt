package br.com.aguiar.misocial.data.service

import br.com.aguiar.misocial.data.model.UsersJson
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    fun getUsersFromService(): Deferred<Response<List<UsersJson>>>

}