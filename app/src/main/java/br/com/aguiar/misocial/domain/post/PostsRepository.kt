package br.com.aguiar.misocial.domain.post

interface PostsRepository {

    suspend fun fetchPosts() : List<Posts>

}