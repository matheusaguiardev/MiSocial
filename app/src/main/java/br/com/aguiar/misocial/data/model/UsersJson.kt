package br.com.aguiar.misocial.data.model

import com.google.gson.annotations.SerializedName

data class UsersJson (
    @SerializedName("id") val id: Int,
    @SerializedName("username") val userName: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String
)