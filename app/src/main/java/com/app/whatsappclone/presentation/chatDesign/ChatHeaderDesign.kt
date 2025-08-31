package com.app.whatsappclone.presentation.chatDesign

import android.text.Layout
import androidx.collection.objectListOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel
import org.w3c.dom.Text
import java.time.LocalDateTime

@Composable
fun ChatHeaderDesign(chatListModel: ChatListModel) {
    val isExpanded = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), horizontalArrangement = Arrangement.Start
    ) {
        Column {
            IconButton(onClick = {}) {
                Icon(
                    Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier.align(
                        Alignment.CenterHorizontally
                    )
                )
            }
        }
        Image(
            painter = painterResource(chatListModel.image),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .align(Alignment.CenterVertically),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = chatListModel.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Row {
                if (chatListModel.isOnline) {
                    Text(text = "Online", fontSize = 12.sp, fontWeight = FontWeight.Normal)
                }
            }
        }
        Spacer(modifier = Modifier.width(50.dp))
        Row(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {}, modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(R.drawable.videocall_icon),
                    contentDescription = "Video Call Icon",
                    modifier = Modifier.size(24.dp)
                )
            }
            IconButton(
                onClick = {}, modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(R.drawable.outline_phone_24),
                    contentDescription = "Voice Call"
                )
            }
            IconButton(
                onClick = { isExpanded.value = true },
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Icon(
                    painter = painterResource(R.drawable.more),
                    contentDescription = "More",
                    modifier = Modifier.size(24.dp)
                )

                DropdownMenu(expanded = isExpanded.value,
                    onDismissRequest = { isExpanded.value = false }) {
                    objectListOf(
                        Text(text = "View Contact"),
                        Text(text = "Search"),
                        Text(text = "New group"),
                        Text(text = "Media, links, and docs"),
                        Text(text = "Mute notifications"),
                        Text(text = "Disapppearing messages"),
                        Text(text = "Chat theme"),
                        Text(text = "More")
                    ).forEach {
                        DropdownMenuItem({
                            it
                        }, onClick = {}, modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
