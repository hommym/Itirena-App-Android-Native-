package com.example.itirena.Feature.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itirena.Feature.presentation.navigation.BottomNavigation
import com.example.itirena.Feature.presentation.resuableComponents.Day
import com.example.itirena.Feature.presentation.resuableComponents.DaysOfWeekTitle
import com.example.itirena.Feature.presentation.resuableComponents.NotificationItem
import com.example.itirena.Feature.presentation.resuableComponents.getMonthName
import com.example.itirena.R
import com.example.itirena.ui.theme.Blue
import com.example.itirena.ui.theme.White
import com.example.itirena.ui.theme.darkModeGray
import com.example.itirena.ui.theme.darkerGray
import com.example.itirena.ui.theme.fontFamily
import com.example.itirena.ui.theme.lightModeGray
import com.example.itirena.ui.theme.lighterGray
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.WeekCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.core.atStartOfMonth
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .background(
                color = if (isSystemInDarkTheme()) darkerGray else White
            )
            .fillMaxSize()
            .padding(
                bottom = WindowInsets.navigationBars
                    .asPaddingValues()
                    .calculateBottomPadding()
            )
    ){

        HomeItems()
        BottomNavigation(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun HomeItems(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                bottom = 70.dp
            )
    ){
//        val currentMonth = remember { YearMonth.now() }
//        val startMonth = remember { currentMonth.minusMonths(100) } // Adjust as needed
//        val endMonth = remember { currentMonth.plusMonths(100) } // Adjust as needed
//        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library

        val currentDate = remember { LocalDate.now() }
        val currentMonth = remember { YearMonth.now() }
        val startDate = remember { currentMonth.minusMonths(100).atStartOfMonth() } // Adjust as needed
        val endDate = remember { currentMonth.plusMonths(100).atEndOfMonth() } // Adjust as needed
        val firstDayOfWeek = remember { firstDayOfWeekFromLocale() } // Available from the library


        val state = rememberWeekCalendarState(
            startDate = startDate,
            endDate = endDate,
            firstVisibleWeekDate = currentDate,
            firstDayOfWeek = firstDayOfWeek
        )

        var monthTitle by remember { mutableIntStateOf(state.lastVisibleWeek.days.last().date.monthValue) }
        

        Spacer(modifier = Modifier.height(WindowInsets.systemBars.asPaddingValues().calculateTopPadding()))
        
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                horizontalAlignment = Alignment.Start
            ){
                Text(
                    text = "2024/2025",
                    fontFamily = fontFamily,
                    color = if(isSystemInDarkTheme()) White else darkerGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "ACADEMIC YEAR",
                    fontFamily = fontFamily,
                    color = if(isSystemInDarkTheme()) White else darkerGray,
                    fontSize = 10.sp
                )
                
            }
            
            Image(
                painter = painterResource(id = R.drawable.sunset), 
                contentDescription = "User profile image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = "Left",
                modifier = Modifier.size(24.dp),
                tint = if (isSystemInDarkTheme()) White else darkerGray
            )

            Text(
                text = getMonthName(monthTitle),
                fontFamily = fontFamily,
                color = if(isSystemInDarkTheme()) White else darkerGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Box(
                modifier = Modifier
                    .background(
                        color = if(isSystemInDarkTheme()) darkModeGray else lightModeGray,
                        shape = RoundedCornerShape(10.dp)
                    )
            ){
                Text(
                    text = "Week",
                    fontFamily = fontFamily,
                    color = if(isSystemInDarkTheme()) White else darkerGray,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = "Left",
                modifier = Modifier.size(24.dp),
                tint = if (isSystemInDarkTheme()) White else darkerGray
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        WeekCalendar(
            state = state,
            dayContent = { Day(it) },
            weekHeader = {
                monthTitle = it.days.last().date.monthValue
                Column {
                    DaysOfWeekTitle(daysOfWeek = it.days)
                }
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn{
            items(10){
                NotificationItem(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    iconID = R.drawable.paper,
                    notificationTitle = " Notification $it",
                    notificationDescription = "This a placeholder text for the notification description"
                )
                Spacer(modifier = Modifier.height(15.dp))
            }

        }
    }

}





