package br.com.aguiar.misocial.repository.repository

import br.com.aguiar.misocial.data.model.UsersJson
import br.com.aguiar.misocial.data.repository.UsersRepositoryImp
import br.com.aguiar.misocial.data.service.UsersApi
import br.com.aguiar.misocial.domain.user.Users
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response

class UsersRepositoryTest {

    private val api = mock<UsersApi>()
    private val repository = UsersRepositoryImp(api)

    private val userList = listOf(
        UsersJson(id = 1, name = "Lucas Calvalca", email = "luquete@gmail.com", userName = "lucasManito"),
        UsersJson(id = 2, name = "Athyla Bezerra", email = "athylovisk@gmail.com", userName = "4thyl4"),
        UsersJson(id = 3, name = "Gabriel Sousa", email = "gabiieu@gmail.com", userName = "Bibi")
    )

    private lateinit var expectedList: List<Users>
    private lateinit var expectedEmptyList: List<Users>

    private val mediaType by lazy {
        MediaType.parse("application/json")
    }

    @Test
    fun `baixar lista de usuarios`() = runBlocking {
        `dado que eu tenha uma lista de usuarios para baixar`(userList)
        `quando eu executar o metodo de chamada de busca de usuarios do servico`()
        `entao verificar se o metodo que executa a logica retornar uma lista de usuarios`()
    }

    @Test
    fun `baixar lista de usuarios porem ocorre algum erro antes ou durante a requisicao`() = runBlocking {
        `dado que eu tenha uma lista de usuarios para baixar e ocorrer um erro`()
        `quando eu executar o metodo de chamada de busca de usuarios do servico e houver error`()
        `entao verificar se a lista retornada esta vazia quando houver qualquer erro de request`()
    }

    /*************** DADO ******************/
    private fun `dado que eu tenha uma lista de usuarios para baixar`(list: List<UsersJson>) = runBlocking {
        whenever(api.getUsersFromService())
            .thenReturn(async { Response.success(list) })
    }

    private fun `dado que eu tenha uma lista de usuarios para baixar e ocorrer um erro`() =
        runBlocking {
            whenever(api.getUsersFromService())
                .thenReturn(async { Response.error<List<UsersJson>>(500, ResponseBody.create(mediaType, "")) })
        }

    /*************** QUANDO ******************/
    private suspend fun `quando eu executar o metodo de chamada de busca de usuarios do servico`() {
        expectedList = repository.fetchUsers()
    }

    private suspend fun `quando eu executar o metodo de chamada de busca de usuarios do servico e houver error`() {
        expectedEmptyList = repository.fetchUsers()
    }

    /*************** ENTÃO ******************/
    private fun `entao verificar se o metodo que executa a logica retornar uma lista de usuarios`() {
        Assert.assertTrue(expectedList.isNotEmpty())
    }

    private fun `entao verificar se a lista retornada esta vazia quando houver qualquer erro de request`() {
        Assert.assertTrue(expectedEmptyList.isEmpty())
    }

}