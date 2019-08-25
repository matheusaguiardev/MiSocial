package br.com.aguiar.misocial.domain.post

class PostsInteractor(
   private val postRepository: PostsRepository
) {
    suspend operator fun invoke()= postRepository.fetchPosts()
}