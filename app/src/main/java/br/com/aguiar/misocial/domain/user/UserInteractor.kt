package br.com.aguiar.misocial.domain.user

import javax.inject.Inject

class UserInteractor @Inject constructor(
   private val repository: UsersRepository
) {
    suspend operator fun invoke() = repository.fetchUsers()
}