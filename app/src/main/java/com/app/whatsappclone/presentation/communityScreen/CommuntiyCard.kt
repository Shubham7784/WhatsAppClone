package com.app.whatsappclone.presentation.communityScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.chatListDesign.ChatDesign

@Composable
fun CommunityCard(communityModel: CommunityModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(modifier = Modifier.padding(10.dp).align(Alignment.Start), horizontalArrangement = Arrangement.SpaceAround) {
            Card(modifier = Modifier.size(60.dp), shape = RoundedCornerShape(12.dp)) {
                Image(
                    painter = painterResource(communityModel.communityImage),
                    contentDescription = "Community Icon"
                )
            }

            Text(
                text = communityModel.communityName,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 10.dp)
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 6.dp))

        Column(
            modifier = Modifier
                .padding(10.dp)
                .height(60.dp)
        ) {
            Row {
                Card(
                    modifier = Modifier.size(60.dp), shape = RoundedCornerShape(12.dp),
                    colors = CardColors(
                        Color(red = 216, green = 253, blue = 210),
                        Color(26, 99, 63),
                        Color(red = 216, green = 253, blue = 210),
                        Color(26, 99, 63)
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.announcement_icon),
                        contentDescription = "Annoucements Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .padding(top = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 10.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            "Announcements",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 1,
                            minLines = 1,
                            color = Color.Black
                        )

                        Text(
                            communityModel.announcementModel.time,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            communityModel.announcementModel.message,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray,
                            maxLines = 1,
                            minLines = 1
                        )

                        Row(horizontalArrangement = Arrangement.SpaceBetween) {

                            if (communityModel.announcementModel.isMuted) {
                                Icon(
                                    painter = painterResource(R.drawable.muted_notification_icon),
                                    contentDescription = "Muted Notification",
                                    modifier = Modifier.size(18.dp),
                                    tint = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.width(5.dp))
                            if (communityModel.announcementModel.isUnread) {
                                Badge(
                                    containerColor = colorResource(R.color.light_green),
                                    modifier = Modifier
                                        .size(10.dp)
                                        .align(
                                            Alignment.CenterVertically
                                        )
                                )
                            }
                        }
                    }
                }
            }

        }
        Row {
            ChatDesign(chatListModel = communityModel.groupModel[0])
        }

        Row(modifier = Modifier.fillMaxWidth())
        {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.CenterVertically),
                colors = ButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.DarkGray,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.DarkGray
                )
            ) {
                Icon(
                    Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = "View All Icon",
                    modifier = Modifier.align(
                        Alignment.CenterVertically
                    )
                )

                Text(
                    "View all",
                    fontWeight = FontWeight.Normal,
                    color = Color.DarkGray,
                    fontSize = 14.sp
                )
            }
        }
    }
}