package br.com.aguiar.misocial.repository.repository

import br.com.aguiar.misocial.data.model.CommentsJson
import br.com.aguiar.misocial.data.repository.CommentsRepositoryImp
import br.com.aguiar.misocial.data.service.CommentsApi
import br.com.aguiar.misocial.domain.comment.Comments
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import retrofit2.Response

class CommentsRepositoryTest {


    private val api = mock<CommentsApi>()
    private val repository = CommentsRepositoryImp(api)

    private val postList = listOf(
        CommentsJson(id = 1, body = "Lorem ipsum", email = "luquete@gmail.com", name = "Lucas Calvalca", postId = 1),
        CommentsJson(id = 2, body = "Lorem ipsum", email = "athylovisk@gmail.com", postId = 2, name = "Athyla Bezerra"),
        CommentsJson(id = 3, body = "Lorem ipsum", email = "gabiieu@gmail.com", postId = 3, name = "Gabriel Sousa")
    )

    private lateinit var expectedList: List<Comments>
    private lateinit var expectedEmptyList: List<Comments>

    private val mediaType by lazy {
        MediaType.parse("application/json")
    }

    @Test
    fun `baixar lista de comentarios de uma postagem`() = runBlocking {
        `dado que eu tenha uma lista de comentarios para baixar`(postList)
        `quando eu executar o metodo de chamada de busca os comentarios do servico`()
        `entao verificar se o metodo que executa a logica retornar uma lista de comentarios`()
    }

    @Test
    fun `baixar lista de comentarios porem ocorre algum erro antes ou durante a requisicao`() = runBlocking {
        `dado que eu tenha uma lista de comentarios para baixar e um erro e esperado`()
        `quando eu executar o metodo de chamada de busca de comentarios do servico com erro`()
        `entao verificar se a lista retornada esta vazia quando houver qualquer erro de request`()
    }

    /*************** DADO ******************/
    private fun `dado que eu tenha uma lista de comentarios para baixar`(list: List<CommentsJson>) = runBlocking {
        whenever(api.getCommentsFromService(any()))
            .thenReturn(async { Response.success(list) })
    }

    private fun `dado que eu tenha uma lista de comentarios para baixar e um erro e esperado`() =
        runBlocking {
            whenever(api.getCommentsFromService(any()))
                .thenReturn(async { Response.error<List<CommentsJson>>(500, ResponseBody.create(mediaType, "")) })
        }

    /*************** QUANDO ******************/
    private suspend fun `quando eu executar o metodo de chamada de busca os comentarios do servico`() {
        expectedList = repository.fetchComments(any())
    }

    private suspend fun `quando eu executar o metodo de chamada de busca de comentarios do servico com erro`() {
        expectedEmptyList = repository.fetchComments(any())
    }

    /*************** ENTÃO ******************/
    private fun `entao verificar se o metodo que executa a logica retornar uma lista de comentarios`() {
        Assert.assertTrue(expectedList.isNotEmpty())
    }

    private fun `entao verificar se a lista retornada esta vazia quando houver qualquer erro de request`() {
        Assert.assertTrue(expectedEmptyList.isEmpty())
    }

}