package br.com.aguiar.misocial.domain.comment

class CommentsInteractor(
    private val repository: CommentsRepository
) {
    suspend operator fun invoke() = repository.fetchComments()
}