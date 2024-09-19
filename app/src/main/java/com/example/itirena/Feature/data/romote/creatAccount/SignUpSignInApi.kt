package com.example.itirena.Feature.data.romote.creatAccount

import com.example.itirena.Feature.data.constants.Constants
import com.example.itirena.Feature.data.romote.creatAccount.sign_up_dto.SignUpResult
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpSignInApi {
    @POST(Constants.SUB_URL+"signup")
    suspend fun signUp(
        @Body signUpBody: SignUpBody
    ):SignUpResult

}

data class SignUpBody(
    val email: String,
    val userName: String,
    val password: String
)