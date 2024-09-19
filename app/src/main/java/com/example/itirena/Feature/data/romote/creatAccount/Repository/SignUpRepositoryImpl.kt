package com.example.itirena.Feature.data.romote.creatAccount.Repository

import com.example.itirena.Feature.data.constants.Constants
import com.example.itirena.Feature.data.romote.creatAccount.SignUpSignInApi
import com.example.itirena.Feature.data.romote.creatAccount.SignUpBody
import com.example.itirena.Feature.data.romote.creatAccount.sign_up_dto.SignUpResult
import com.example.itirena.Feature.domain.Resource
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpApi: SignUpSignInApi
): SignUpSignInRepository{
    override suspend fun signUp(
        signUpBody: SignUpBody
    ): Resource<SignUpResult> {
        Resource.Loading(data = null)
       return try {
           val result = signUpApi.signUp(signUpBody)
           Resource.Success(result)
       }catch (e: Exception){
           Resource.Error(e.message.toString())
       }
    }
}