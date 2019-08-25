package br.com.aguiar.misocial.domain.user

class UserInteractor(
   private val repository: UsersRepository
) {
    suspend operator fun invoke() = repository.fetchUsers()
}