package br.com.aguiar.misocial.domain.post

import javax.inject.Inject

class PostsInteractor @Inject constructor(
   private val postRepository: PostsRepository
) {
    suspend operator fun invoke()= postRepository.fetchPosts()
}