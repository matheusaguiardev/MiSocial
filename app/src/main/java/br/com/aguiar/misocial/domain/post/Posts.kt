package br.com.aguiar.misocial.domain.post

data class Posts(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var createdBy: String = "Guest"
)