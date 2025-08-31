package com.app.whatsappclone.presentation.communityScreen

import androidx.collection.objectListOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.whatsappclone.presentation.bottomNavigation.BottomNavigation
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel

@Composable
fun CommunityScreen(navController: NavHostController) {

    val verticalScrollState = rememberScrollState()

    val groupData = objectListOf<ChatListModel>(
        ChatListModel(
            R.drawable.profile_placeholder,
            "Group Name",
            "10:00 AM",
            false,
            0,
            false
        )
    )

    val communitiesData = objectListOf(
        CommunityModel(
            R.drawable.profile_placeholder, "Community Name",
            AnnouncementModel("Hello", "10:00 AM", false, false), groupData
        ),
        CommunityModel(
            R.drawable.profile_placeholder,
            "Community Name",
            AnnouncementModel("Hello", "10:00 AM", false, false),
            groupData
        )
    )

    Scaffold(
        topBar = {
            CommunityTopBar()
        },
        bottomBar = {
            BottomNavigation()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(verticalScrollState)
        ) {
            Row(modifier = Modifier.padding(10.dp)) {
                Card(
                    modifier = Modifier
                        .size(60.dp),
                    colors = CardColors(
                        containerColor = Color.LightGray,
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.BottomEnd
                    )
                    {
                        Icon(
                            painter = painterResource(R.drawable.communities_icon),
                            contentDescription = "Community Icon",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 8.dp, bottom = 8.dp)
                                .size(45.dp)
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .background(
                                    color = colorResource(R.color.light_green),
                                    shape = CircleShape
                                )
                        )
                        {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add Status",
                                tint = Color.White,
                            )
                        }

                    }
                }

                Text(
                    text = "New Community", fontWeight = FontWeight.Normal, fontSize = 16.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 10.dp)
                )

            }
            HorizontalDivider()
            communitiesData.forEach {
                Spacer(
                    modifier = Modifier
                        .height(6.dp)
                        .background(Color.LightGray)
                )
                CommunityCard(it)
            }
        }
    }
}