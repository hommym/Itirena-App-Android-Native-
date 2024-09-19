package com.example.itirena.Feature.di

import com.example.itirena.Feature.data.constants.Constants
import com.example.itirena.Feature.data.romote.creatAccount.Repository.SignUpRepositoryImpl
import com.example.itirena.Feature.data.romote.creatAccount.Repository.SignUpSignInRepository
import com.example.itirena.Feature.data.romote.creatAccount.SignUpSignInApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RemoteHiltModule {

    @Singleton
    @Provides
    fun provideSignUpSignInApi(@Named("SIGNUP_SIGN_IN") builder: Retrofit.Builder): SignUpSignInApi{
        return builder
            .build()
            .create(SignUpSignInApi::class.java)
    }

    @Singleton
    @Provides
    @Named("SIGNUP_SIGN_IN")
    fun provideSignUpSignInRetrofit(): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    fun provideSignUpSignInRepository(signUpSignInApi: SignUpSignInApi): SignUpSignInRepository{
       return SignUpRepositoryImpl(signUpSignInApi)
    }


}