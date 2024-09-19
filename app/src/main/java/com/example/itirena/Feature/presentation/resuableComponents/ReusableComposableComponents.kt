package com.example.itirena.Feature.presentation.resuableComponents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itirena.ui.theme.Blue
import com.example.itirena.ui.theme.White
import com.example.itirena.ui.theme.darkModeGray
import com.example.itirena.ui.theme.darkerGray
import com.example.itirena.ui.theme.fontFamily
import com.example.itirena.ui.theme.lightModeGray
import com.example.itirena.ui.theme.lighterGray
import com.kizitonwose.calendar.core.WeekDay
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Invalid month" // Handles any invalid input
    }
}

@Composable
fun NotificationItem(
    modifier: Modifier = Modifier,
    iconID: Int,
    notificationTitle: String,
    notificationDescription: String
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 5.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = if (isSystemInDarkTheme()) darkModeGray else lightModeGray,
                shape = RoundedCornerShape(20.dp)
            )
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconID),
            contentDescription = "",
            tint = if(isSystemInDarkTheme()) White.copy(alpha = 0.8f) else darkerGray,
            modifier = Modifier
                .padding(start = 10.dp)
                .size(25.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 20.dp)
        ) {
            Text(
                text = notificationTitle,
                fontFamily = fontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if(isSystemInDarkTheme()) White.copy(alpha = 0.8f) else darkerGray
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = notificationDescription,
                fontFamily = fontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = if(isSystemInDarkTheme()) White.copy(alpha = 0.8f) else darkerGray
            )
        }
    }
}

@Composable
fun Day(day: WeekDay, selected: Boolean =false) {
    Box(
        modifier = Modifier
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .background(
                    shape = CircleShape,
                    color = if (day.date == LocalDate.now()) {
                        Blue
                    } else {
                        if (selected) lighterGray else Color.Transparent
                    }
                ),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = day.date.dayOfMonth.toString(),
                color =  if (day.date == LocalDate.now()) {
                    White
                } else {
                    if (isSystemInDarkTheme()) White else darkerGray
                },
                fontFamily = fontFamily,
                modifier = Modifier.padding(5.dp)
            )
        }

    }
}

@Composable
fun CustomBtn(
    modifier: Modifier = Modifier,
    btnBackground: Color,
    btnText: String = "Click me",
    btnLeadingIcon: Int? = null,
    isLoading: Boolean = false,
    height: Dp = 45.dp,
    textColor: Color = darkerGray,
    cornerRadius: Dp = 10.dp,
    onClick: () -> Unit = {}
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                color = btnBackground,
                shape = RoundedCornerShape(cornerRadius)
            )
            .clip(RoundedCornerShape(cornerRadius))
            .clickable {
                if (!isLoading) {
                    onClick()
                }
            }
    ){
        if(btnLeadingIcon != null){
            Image(
                painter = painterResource(id = btnLeadingIcon),
                contentDescription = "leading icon image",
                modifier = Modifier
                    .padding(10.dp)
                    .size(30.dp)
                    .align(Alignment.CenterStart)
            )
        }

        AnimatedVisibility(
            visible = isLoading,
            modifier = Modifier.align(Alignment.Center),
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            CircularLoadingIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(30.dp)
            )
        }
        AnimatedVisibility(
            visible = !isLoading,
            modifier = Modifier.align(Alignment.Center),
            enter = scaleIn(),
            exit = scaleOut()
        ){
            Text(
                text = btnText,
                fontFamily = fontFamily,
                color = textColor,
                modifier = Modifier
                    .align(Alignment.Center),
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun CircularLoadingIndicator(
    modifier: Modifier = Modifier,
    loadingColor: Color = White,
    backgroundColor: Color = Gray,
    progressWidth: Dp = 5.dp,
){
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val degrees by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "Infinite Colors"
    )
    CircularProgressBar(
        modifier = modifier.rotate(degrees),
        progress = 30f,
        progressMax = 100f,
        progressBarWidth = progressWidth,
        progressBarColor = loadingColor,
        backgroundProgressBarColor = backgroundColor,
        roundBorder = true
    )
}
@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    progressMax: Float = 100f,
    progressBarColor: Color = Color.Black,
    progressBarWidth: Dp = 7.dp,
    backgroundProgressBarColor: Color = Color.Gray,
    backgroundProgressBarWidth: Dp = 3.dp,
    roundBorder: Boolean = false,
    startAngle: Float = 0f
) {
    Canvas(modifier = modifier.fillMaxSize()) {

        val canvasSize = size.minDimension

        val radius = canvasSize / 2 - maxOf(backgroundProgressBarWidth, progressBarWidth).toPx() / 2

        drawCircle(
            color = backgroundProgressBarColor,
            radius = radius,
            center = size.center,
            style = Stroke(width = backgroundProgressBarWidth.toPx())
        )

        drawArc(
            color = progressBarColor,
            startAngle = 270f + startAngle,
            sweepAngle = (progress / progressMax) * 360f,
            useCenter = false,
            topLeft = size.center - Offset(radius, radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(
                width = progressBarWidth.toPx(),
                cap = if (roundBorder) StrokeCap.Round else StrokeCap.Butt
            )
        )
    }
}

@Composable
fun DaysOfWeekTitle(daysOfWeek: List<WeekDay>) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayOfWeek in daysOfWeek) {

            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = dayOfWeek.date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                fontFamily = fontFamily,
                color = Color.Gray
            )
        }
    }
}