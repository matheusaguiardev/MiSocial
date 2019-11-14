package br.com.aguiar.misocial.data.repository

import br.com.aguiar.misocial.data.mapper.toDomain
import br.com.aguiar.misocial.data.service.UsersApi
import br.com.aguiar.misocial.domain.user.Users
import br.com.aguiar.misocial.domain.user.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(private val api: UsersApi) : UsersRepository {

    override suspend fun fetchUsers(): List<Users> {
        return try {
            val result = withContext(Dispatchers.IO) {
                api.getUsersFromService()
            }.await()

            result.body()?.let { users ->
                return if (result.isSuccessful) {
                    users.map { it.toDomain() }
                } else {
                    emptyList()
                }
            }
            return emptyList()
        } catch (e: Throwable) {
            emptyList()
        }
    }
}