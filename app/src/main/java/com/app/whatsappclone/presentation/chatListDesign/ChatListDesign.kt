package com.app.whatsappclone.presentation.chatListDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R

@Composable
fun ChatDesign(chatListModel: ChatListModel,onClick: @Composable () -> Unit = {}) {
    Row(
        modifier = Modifier.padding(10.dp, top = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(chatListModel.image), contentDescription = "Profile Image",
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape)
                .clickable(true, onClick = {}),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.clickable(true, onClick = {
            onClick
        })) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = chatListModel.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )

                Text(text = chatListModel.time, color = Color.DarkGray)
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween){

                if(!chatListModel.messages.isEmpty()){
                    Text(
                        text = chatListModel.messages.peek().message,
                        color = Color.DarkGray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        maxLines = 1
                    )
                }


                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.align(
                    Alignment.CenterVertically).fillMaxWidth()) {

                    if(chatListModel.isMuted){
                        Icon(
                            painter = painterResource(R.drawable.muted_notification_icon),
                            contentDescription = "Muted Notification",
                            modifier = Modifier.size(18.dp),
                            tint = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))
                    if(chatListModel.isUnread){
                        Badge(containerColor = colorResource(R.color.light_green))
                        {
                            Text(
                                text = chatListModel.unreadCount.toString(),
                                color = Color.White,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(1.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}