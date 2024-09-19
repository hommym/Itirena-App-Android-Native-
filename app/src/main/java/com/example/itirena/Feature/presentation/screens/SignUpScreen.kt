package com.example.itirena.Feature.presentation.screens

import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.itirena.Feature.data.romote.creatAccount.SignUpBody
import com.example.itirena.Feature.domain.ViewModel.SignUpViewModel
import com.example.itirena.Feature.presentation.resuableComponents.CustomBtn
import com.example.itirena.R
import com.example.itirena.ui.theme.Blue
import com.example.itirena.ui.theme.White
import com.example.itirena.ui.theme.darkModeGray
import com.example.itirena.ui.theme.darkerGray
import com.example.itirena.ui.theme.fontFamily
import com.example.itirena.ui.theme.lightModeGray
import com.example.itirena.ui.theme.lighterGray

@Composable
fun SignUpScreen(
    signUpSignUpViewModel: SignUpViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val signUpState = signUpSignUpViewModel.movieDetailsHomeScreenState.collectAsState().value


    val infiniteTransition = rememberInfiniteTransition(label = "")

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val brush = remember(offset) {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val widthOffset = size.width * offset
                val heightOffset = size.height * offset
                return LinearGradientShader(
                    colors = listOf(White, Blue, Blue, Blue, Blue),
                    from = Offset(widthOffset, heightOffset),
                    to = Offset(widthOffset + size.width , heightOffset + size.height),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (isSystemInDarkTheme()) darkerGray else White
            )
    ){
        Box(
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopEnd)
                .offset(x = 200.dp, y = -(200).dp)
                .background(
                    brush = Brush.radialGradient(
                        listOf(
                            Blue.copy(alpha = 0.4f),
                            Blue.copy(alpha = 0.3f),
                            Blue.copy(alpha = 0.2f),
                            Blue.copy(alpha = 0.1f),
                            if (isSystemInDarkTheme()) darkerGray else White
                        )
                    )
                )
        )

        Box(
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.BottomStart)
                .offset(y = 200.dp, x = -(200).dp)
                .background(
                    brush = Brush.radialGradient(
                        listOf(
                            Blue.copy(alpha = 0.4f),
                            Blue.copy(alpha = 0.3f),
                            Blue.copy(alpha = 0.2f),
                            Blue.copy(alpha = 0.1f),
                            if (isSystemInDarkTheme()) darkerGray else White
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
               ,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            Column {

                Text(
                    text = "Welcome,",
                    modifier = Modifier.padding(horizontal = 20.dp),
//                    color = if(isSystemInDarkTheme()) lightModeGray else darkerGray
                    style = TextStyle(
                        brush = brush,
                        fontSize = 40.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                    )
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Sign up to get started!",
                    fontSize = 18.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(50.dp))
            }


            Column {
                OutlinedTextField(
                    value = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    onValueChange = {},
                    label = { Text(text = "Full name") },
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Gray,
                        focusedIndicatorColor = Blue,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.Gray,

                        )
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    onValueChange = {},
                    label = { Text(text = "Email ID") },
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Gray,
                        focusedIndicatorColor = Blue,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )
                Spacer(modifier = Modifier.height(5.dp))

                OutlinedTextField(
                    value = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    onValueChange = {},
                    label = { Text(text = "Password") },
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        unfocusedIndicatorColor = Color.Gray,
                        focusedIndicatorColor = Blue,
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Color.Gray
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    )
                )

            }

            Column {

                CustomBtn(
                    btnBackground = Blue,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    cornerRadius = 15.dp,
                    btnText = "Sign Up",
                    isLoading = signUpState.isLoading,
                    onClick = {
                        signUpSignUpViewModel.signUp(
                            signUpBody = SignUpBody(
                                email = "gideon@gmail.com",
                                userName = "GideonEyiahYaw",
                                password = "12345678aaaaa"
                            ),
                            onSuccess = {
                                Toast
                                    .makeText(
                                        context,
                                        signUpSignUpViewModel.movieDetailsHomeScreenState.value.data.message,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            },
                            onError = {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                )


                Box{
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 15.dp)
                    )
                    Text(
                        text = "or",
                        fontFamily = fontFamily,
                        color = if(isSystemInDarkTheme()) White else darkModeGray,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(
                                color = if (isSystemInDarkTheme()) darkerGray else White
                            )
                            .padding(horizontal = 10.dp),
                        fontSize = 16.sp
                    )
                }


                CustomBtn(
                    btnBackground = lightModeGray,
                    btnLeadingIcon = R.drawable.google_icon,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    cornerRadius = 15.dp,
                    btnText = "Continue with Google",
                    onClick = {

                    }
                )

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Already a member? ",
                    fontFamily = fontFamily,
                    color =  if(isSystemInDarkTheme()) White else darkModeGray,
                    modifier = Modifier,
                    fontSize = 16.sp
                )
                Text(
                    text = "Sign In",
                    fontFamily = fontFamily,
                    color = Blue,
                    modifier = Modifier,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()))

        }

    }
}

@Composable
@Preview
private fun SignUpScreenPreview(){
    SignUpScreen()
}