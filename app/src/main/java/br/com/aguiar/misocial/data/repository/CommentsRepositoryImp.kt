package br.com.aguiar.misocial.data.repository

import br.com.aguiar.misocial.data.mapper.toDomain
import br.com.aguiar.misocial.data.service.CommentsApi
import br.com.aguiar.misocial.domain.comment.Comments
import br.com.aguiar.misocial.domain.comment.CommentsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CommentsRepositoryImp(private val api: CommentsApi) : CommentsRepository {

    override suspend fun fetchComments(): List<Comments> {
        return try {
            val result = withContext(Dispatchers.IO) {
                api.getCommentsFromService()
            }.await()

            result.body()?.let { comments ->
                return if (result.isSuccessful) {
                    comments.map { it.toDomain() }
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