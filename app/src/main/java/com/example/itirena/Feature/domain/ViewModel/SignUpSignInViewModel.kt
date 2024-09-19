package com.example.itirena.Feature.domain.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itirena.Feature.data.romote.creatAccount.Repository.SignUpSignInRepository
import com.example.itirena.Feature.data.romote.creatAccount.SignUpBody
import com.example.itirena.Feature.data.romote.creatAccount.sign_up_dto.SignUpResult
import com.example.itirena.Feature.domain.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpSignInRepository: SignUpSignInRepository
): ViewModel(){

    private val _signUpResultState = MutableStateFlow(SignUpResultState())
    val movieDetailsHomeScreenState: StateFlow<SignUpResultState> = _signUpResultState


    fun signUp(
        signUpBody: SignUpBody,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = { _ ->}
    ) = viewModelScope.launch {
        try {
            _signUpResultState.update {
                it.copy(isLoading = true)
            }
            when(val result = signUpSignInRepository.signUp(signUpBody)){
                is Resource.Error -> {
                    _signUpResultState.update {
                        it.copy(
                            failedToSignUp = true,
                            errorMsg = result.message.toString(),
                            isLoading = false,
                            signedUp = false
                        )
                    }
                    onError(result.message.toString())
                }
                is Resource.Success -> {
                    result.data?.let {
                        _signUpResultState.update {
                            it.copy(
                                signedUp = true,
                                data = result.data,
                                isLoading = false,
                                failedToSignUp = false
                            )
                        }
                    }
                    onSuccess()
                }
                is Resource.Loading -> {
                    _signUpResultState.update {
                        it.copy(
                            isLoading = true,
                            failedToSignUp = false,
                            signedUp = false,
                        )
                    }
                }
            }
        }catch (e:Exception){
            _signUpResultState.update {
                it.copy(
                    failedToSignUp = true,
                    isLoading = false,
                    signedUp = false,
                    errorMsg = e.message.toString()
                )
            }
            onError(e.message.toString())
        }
    }
}

data class SignUpResultState(
    val isLoading: Boolean = false,
    val signedUp: Boolean = false,
    val failedToSignUp: Boolean = false,
    val errorMsg: String = "",
    val data: SignUpResult = SignUpResult(
        message = ""
    )
)