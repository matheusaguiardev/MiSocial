package br.com.aguiar.misocial.domain.user

interface UsersRepository {

    suspend fun fetchUsers() : List<Users>

}