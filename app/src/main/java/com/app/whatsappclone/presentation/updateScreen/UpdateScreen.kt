package com.app.whatsappclone.presentation.updateScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FloatingActionButton
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
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.bottomNavigation.BottomNavigation
import com.app.whatsappclone.presentation.chatListDesign.ChatDesign
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel
import java.util.ArrayList


@Composable
fun UpdateScreen(navController: NavHostController) {
    val updateData = ArrayList<UpdateDataModel>()
    updateData.add(UpdateDataModel(R.drawable.carryminati, "Carryminati", "10:00 AM", true))
    updateData.add(UpdateDataModel(R.drawable.salman_khan, "Salman Bhoi", "10:00 AM", true))
    updateData.add(UpdateDataModel(R.drawable.sharukh_khan, "Shahrukh Bhoi", "10:00 AM", true))

    val channelsData = ArrayList<ChatListModel>()
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "10:00 AM",
            false,
            0
        ))
    channelsData.add(ChatListModel(R.drawable.profile_placeholder, "Channel Name", "10:00 AM", false, 0))

    val newChannelsData = ArrayList<NewChannelsListModel>()
    newChannelsData.add(NewChannelsListModel(
            R.drawable.profile_placeholder,
            "Channel Name",
            "1000 Followers",
            true
        ))
        newChannelsData.add(NewChannelsListModel(R.drawable.profile_placeholder, "Channel Name", "1000 Followers"))
        newChannelsData.add(NewChannelsListModel(R.drawable.profile_placeholder, "Channel Name", "1000 Followers"))
        newChannelsData.add(NewChannelsListModel(R.drawable.profile_placeholder, "Channel Name", "1000 Followers"))

    val verticalScrollState = rememberScrollState()

    val horizontalScrollState = rememberScrollState()

    Scaffold(
        bottomBar = { BottomNavigation() },
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = {},
                    modifier = Modifier
                        .size(45.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(R.drawable.pencil_icon),
                        contentDescription = "Write Status",
                        modifier = Modifier.size(35.dp)
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                FloatingActionButton(
                    onClick = {},
                    containerColor = colorResource(R.color.light_green),
                    modifier = Modifier.size(65.dp)
                )
                {
                    Icon(
                        painter = painterResource(R.drawable.camera),
                        contentDescription = "Add Status with Camera",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp),
                        tint = colorResource(R.color.white)
                    )
                }
            }
        },
        topBar = {
            UpdateScreenTopBar()
        },
        modifier = Modifier.padding(top = 10.dp)
    )
    { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(verticalScrollState)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Status", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .horizontalScroll(horizontalScrollState),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                UpdateCardSelf(R.drawable.hrithik_roshan, onClick = {})

                for (model in updateData) {
                    UpdateCardOther(model)
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Channels",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                )

                Button(
                    onClick = {},
                    shape = CircleShape,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    colors =
                    ButtonColors(
                        containerColor = Color(246, 245, 243),
                        contentColor = Color.Black,
                        disabledContainerColor = Color(246, 245, 243),
                        disabledContentColor = Color.Black
                    )
                ) {
                    Text(text = "Explore", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                for (model in channelsData) {
                    ChatDesign(model)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Find channels to follow",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Column {
                for (model in newChannelsData) {
                    NewChannelDesign(model)
                }
            }

            Row(modifier = Modifier.padding(10.dp)) {
                Button(
                    onClick = {},
                    shape = CircleShape,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    colors =
                    ButtonColors(
                        containerColor = Color(246, 245, 243),
                        contentColor = Color.Black,
                        disabledContainerColor = Color(246, 245, 243),
                        disabledContentColor = Color.Black
                    ),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Text(text = "Explore", fontSize = 14.sp)
                }
            }

        }
    }
}