package br.com.aguiar.misocial.data.model

import com.google.gson.annotations.SerializedName

data class PostsJson(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)