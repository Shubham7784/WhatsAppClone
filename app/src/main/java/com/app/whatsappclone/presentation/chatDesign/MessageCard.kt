package com.app.whatsappclone.presentation.chatDesign

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
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
import com.app.whatsappclone.R

@Composable
fun OutgoingMessageCard(messageModel: MessageModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.End),
            shape = RoundedCornerShape(10.dp),
            colors = CardColors(
                containerColor = colorResource(R.color.dark_green),
                contentColor = Color.White,
                disabledContentColor = Color.White,
                disabledContainerColor = colorResource(R.color.dark_green)
            )
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp, end = 100.dp, top = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = messageModel.message,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                var time = ""
                if(messageModel.time.hour <12 )
                    time += (messageModel.time.hour).toString() + ":" + messageModel.time.minute+ "AM"
                else if(messageModel.time.hour == 12)
                    time+= "12"+":" + messageModel.time.minute.toString() + "PM"
                else
                    time += (messageModel.time.hour%12).toString() + ":" + messageModel.time.minute.toString() + "PM"
                Text(
                    text = time,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                if (messageModel.isWaiting)
                    Icon(
                        painter = painterResource(R.drawable.waiting_icon),
                        contentDescription = "Message Waiting Icon",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp).padding(start = 4.dp)
                    )
                else if (messageModel.isSent)
                    Icon(Icons.Default.Done, contentDescription = "Message Sent Icon",
                        modifier = Modifier.size(20.dp).padding(start = 4.dp), tint = Color.White)
                else if (messageModel.isDelivered)
                    Icon(
                        painter = painterResource(R.drawable.double_tick),
                        contentDescription = "Message Delivered Icon",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp).padding(start = 4.dp)
                    )
                else if (messageModel.isRead)
                    Icon(
                        painter = painterResource(R.drawable.double_tick),
                        contentDescription = "Message Readed Icon",
                        modifier = Modifier.size(20.dp).padding(start = 4.dp),
                        tint = Color.Blue
                    )

            }
        }
    }
}


@Composable
fun IncomingMessageCard(messageModel: MessageModel) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.Start),
            shape = RoundedCornerShape(10.dp),
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.White
            )
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp, end = 80.dp, top = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = messageModel.message,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                var time = ""
                if(messageModel.time.hour <=12 )
                    time += (messageModel.time.hour).toString() +":" + messageModel.time.minute+ "AM"
                else if(messageModel.time.hour == 0)
                    time+= "12"+ ":"+ messageModel.time.minute.toString() + "PM"
                else
                    time += (messageModel.time.hour%12).toString() + ":" + messageModel.time.minute.toString() + "PM"
                Text(
                    text = time,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}
