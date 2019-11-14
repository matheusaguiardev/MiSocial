package br.com.aguiar.misocial.domain.comment

import javax.inject.Inject

class CommentsInteractor @Inject constructor(
    private val repository: CommentsRepository
) {
    suspend operator fun invoke(postId: Int) = repository.fetchComments(postId)
}