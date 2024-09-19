package com.example.itirena.Feature.data.romote.creatAccount.Repository

import com.example.itirena.Feature.data.romote.creatAccount.SignUpBody
import com.example.itirena.Feature.data.romote.creatAccount.sign_up_dto.SignUpResult
import com.example.itirena.Feature.domain.Resource

interface SignUpSignInRepository {
    suspend fun signUp(
        signUpBody: SignUpBody
    ):Resource<SignUpResult>
}