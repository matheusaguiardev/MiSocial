package br.com.aguiar.misocial.domain.comment

interface CommentsRepository {

    suspend fun fetchComments(postId: Int): List<Comments>

}