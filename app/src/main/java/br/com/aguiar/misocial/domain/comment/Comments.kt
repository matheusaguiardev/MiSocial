package br.com.aguiar.misocial.domain.comment

data class Comments(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String,
    var createdBy: String = "Guest"
)