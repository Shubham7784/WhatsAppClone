package com.app.whatsappclone.presentation.chatDesign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel
import com.app.whatsappclone.R
import java.time.LocalDateTime

@Composable
fun ChatScreen(chatListModel: ChatListModel) {
    val verticalScrollState = rememberScrollState()
    Scaffold(
        topBar = {
            ChatHeaderDesign(
                chatListModel
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
        {
            Image(
                painter = painterResource(R.drawable.whatsapp_chat_background),
                "Chat Wallpaper",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.verticalScroll(verticalScrollState)) {

                Card(modifier = Modifier
                    .width(240.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(6.dp),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Row(modifier = Modifier.padding(4.dp)) {
                        Icon(Icons.Default.Lock, contentDescription = "Encrypted Icon", modifier = Modifier.size(20.dp))
                        Text(
                            text = "Messages are end-to-end encrypted. Only people in the chat can read, listen to, or share them. Select to learn more.",
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }

                for (model in chatListModel.messages) {
                    if(model.isIncoming)
                        IncomingMessageCard(model)
                    else
                        OutgoingMessageCard(model)
                }

            }
            Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                ChatBottomSection()
            }

        }
    }
}