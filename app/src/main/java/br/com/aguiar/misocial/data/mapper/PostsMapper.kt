package br.com.aguiar.misocial.data.mapper

import br.com.aguiar.misocial.data.model.PostsJson
import br.com.aguiar.misocial.domain.post.Posts

fun PostsJson.toDomain() = Posts(
    userId = this.userId,
    id = this.id,
    body = this.body,
    title = this.title
)
