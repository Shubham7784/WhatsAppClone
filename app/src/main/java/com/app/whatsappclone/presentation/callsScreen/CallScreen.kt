package com.app.whatsappclone.presentation.callsScreen

import androidx.collection.objectListOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.whatsappclone.presentation.bottomNavigation.BottomNavigation
import com.app.whatsappclone.R
import java.time.LocalDateTime

@Composable
fun CallScreen(navController: NavHostController) {

    val verticalScrollState = rememberScrollState()
    
    val callData = objectListOf(CallDataModel("Name","Number",true, LocalDateTime.now(),true,false,true,2),
        CallDataModel("Name","Number",false, LocalDateTime.now(),false,true,false,0),
        CallDataModel("Name","Number",true, LocalDateTime.now(),false,false,false,2),
        CallDataModel("Name","Number",false, LocalDateTime.now(),false,true,true,0),)
    Scaffold(
        topBar = { CallTopBar() },
        bottomBar = { BottomNavigation() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(verticalScrollState),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Favorites",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                )
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clickable(enabled = true, onClick = {}),
                horizontalArrangement = Arrangement.Start
            )
            {
                Badge(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterVertically),
                    containerColor = colorResource(R.color.light_green)
                )
                {
                    Image(
                        painterResource(R.drawable.heart_icon),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "Favorite Icon",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Text(
                    text = "Add favorite",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth())
            {
                Text(text = "Recent",fontSize = 18.sp, fontWeight = FontWeight.Normal)
            }
            Column {
                callData.forEach{
                    CallDesign(it)
                }
            }
        }

    }
}