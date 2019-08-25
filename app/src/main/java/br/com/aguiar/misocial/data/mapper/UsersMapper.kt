package br.com.aguiar.misocial.data.mapper

import br.com.aguiar.misocial.data.model.UsersJson
import br.com.aguiar.misocial.domain.user.Users

fun UsersJson.toDomain() = Users(
    id = id,
    name = name,
    email = email,
    userName = userName
)