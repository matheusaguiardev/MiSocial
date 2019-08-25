package br.com.aguiar.misocial.data.mapper

import br.com.aguiar.misocial.data.model.CommentsJson
import br.com.aguiar.misocial.domain.comment.Comments

fun CommentsJson.toDomain() = Comments(
    id = this.id,
    body = this.body,
    email = this.email,
    name = this.name,
    postId = this.postId
)