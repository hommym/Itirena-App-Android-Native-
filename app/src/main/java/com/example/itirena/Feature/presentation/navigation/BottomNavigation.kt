package com.example.itirena.Feature.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itirena.R
import com.example.itirena.ui.theme.Blue
import com.example.itirena.ui.theme.White
import com.example.itirena.ui.theme.darkerGray
import com.example.itirena.ui.theme.fontFamily

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                color = if (isSystemInDarkTheme()) darkerGray else White
            )
            .padding(horizontal = 20.dp, vertical = 10.dp)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomNavigationItems(
            iconId = R.drawable.round_home,
            iconName = "Home",
            selected = true,
            onClick = {}
        )
        BottomNavigationItems(
            iconId = R.drawable.course_material,
            iconName = "Materials",
            selected = false,
            onClick = {}
        )
        BottomNavigationItems(
            iconId = R.drawable.navigation,
            iconName = "Navigation",
            selected = false,
            onClick = {}
        )
        BottomNavigationItems(
            iconId = R.drawable.question_answer,
            iconName = "Q & A",
            selected = false,
            onClick = {}
        )
    }
}

@Composable
private fun BottomNavigationItems(
    iconId: Int,
    iconName: String,
    size: Dp = 25.dp,
    selected: Boolean = false,
    onClick: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = iconName,
            modifier = Modifier.size(size),
            tint = if(selected){
                Blue
            }else{
                if(isSystemInDarkTheme()) White else darkerGray
            }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = iconName,
            fontFamily = fontFamily,
            fontSize = 14.sp,
            color = if(selected){
                Blue
            }else{
                if(isSystemInDarkTheme()) White else darkerGray
            }
        )
    }
}