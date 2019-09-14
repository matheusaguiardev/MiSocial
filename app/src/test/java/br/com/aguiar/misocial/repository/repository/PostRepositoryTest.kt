package br.com.aguiar.misocial.repository.repository

import br.com.aguiar.misocial.data.model.PostsJson
import br.com.aguiar.misocial.data.repository.PostsRepositoryImp
import br.com.aguiar.misocial.data.service.PostsApi
import br.com.aguiar.misocial.domain.post.Posts
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class PostRepositoryTest {

    private val api = mock<PostsApi>()
    private val repository = PostsRepositoryImp(api)

    private val postList = listOf(
        PostsJson(id = 1, body = "Lorem ipsum", title = "Crescem empregos remotos", userId = 1),
        PostsJson(id = 2, body = "Lorem ipsum", title = "Carro pega fogo na avenida", userId = 2),
        PostsJson(id = 3, body = "Lorem ipsum", title = "Cão fala abacate", userId = 3)
    )

    private lateinit var expectedList: List<Posts>
    private lateinit var expectedEmptyList: List<Posts>

    private val mediaType by lazy {
        MediaType.parse("application/json")
    }

    @Test
    fun `baixar lista de postagens`() = runBlocking {
        `dado que eu tenha uma lista de postagens para baixar`(postList)
        `quando eu executar o metodo de chamada de busca de postagens do servico`()
        `entao verificar se o metodo que executa a logica retornar uma lista de postagens`()
    }

    @Test
    fun `baixar lista de postagens porem ocorre algum erro antes ou durante a requisicao`() = runBlocking {
        `dado que eu tenha uma lista de postagens para baixar e um erro e esperado`()
        `quando eu executar o metodo de chamada de busca de postagens do servico com erro`()
        `entao verificar se a lista retornada esta vazia quando houver qualquer erro de request`()
    }

    /*************** DADO ******************/
    private fun `dado que eu tenha uma lista de postagens para baixar`(list: List<PostsJson>) = runBlocking {
        whenever(api.getPostsFromService())
            .thenReturn(async { Response.success(list) })
    }

    private fun `dado que eu tenha uma lista de postagens para baixar e um erro e esperado`() =
        runBlocking {
            whenever(api.getPostsFromService())
                .thenReturn(async { Response.error<List<PostsJson>>(500, ResponseBody.create(mediaType, "")) })
        }

    /*************** QUANDO ******************/
    private suspend fun `quando eu executar o metodo de chamada de busca de postagens do servico`() {
        expectedList = repository.fetchPosts()
    }

    private suspend fun `quando eu executar o metodo de chamada de busca de postagens do servico com erro`() {
        expectedEmptyList = repository.fetchPosts()
    }

    /*************** ENTÃO ******************/
    private fun `entao verificar se o metodo que executa a logica retornar uma lista de postagens`() {
        Assert.assertTrue(expectedList.isNotEmpty())
    }

    private fun `entao verificar se a lista retornada esta vazia quando houver qualquer erro de request`() {
        Assert.assertTrue(expectedEmptyList.isEmpty())
    }


}