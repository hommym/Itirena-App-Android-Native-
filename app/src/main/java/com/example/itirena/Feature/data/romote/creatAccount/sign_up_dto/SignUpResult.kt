package com.example.itirena.Feature.data.romote.creatAccount.sign_up_dto


import com.google.gson.annotations.SerializedName

data class SignUpResult(
    @SerializedName("errType")
    val errType: String? = null,
    @SerializedName("message")
    val message: String
)