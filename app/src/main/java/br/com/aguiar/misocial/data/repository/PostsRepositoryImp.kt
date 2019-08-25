package br.com.aguiar.misocial.data.repository

import br.com.aguiar.misocial.data.mapper.toDomain
import br.com.aguiar.misocial.data.service.PostsApi
import br.com.aguiar.misocial.domain.post.Posts
import br.com.aguiar.misocial.domain.post.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostsRepositoryImp(private val api: PostsApi) : PostsRepository {

    override suspend fun fetchPosts(): List<Posts> {
        return try {
            val result = withContext(Dispatchers.IO) {
                api.getPostsFromService()
            }.await()

            result.body()?.let { posts ->
                return if (result.isSuccessful) {
                    posts.map { it.toDomain() }
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