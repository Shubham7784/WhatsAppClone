package com.app.whatsappclone.presentation.bottomNavigation

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
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.whatsappclone.R

@Composable
@Preview(showSystemUi = true)
fun BottomNavigation() {
    BottomAppBar(tonalElevation = 12.dp, containerColor = Color.White) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Icon(
                    painter = painterResource(R.drawable.chat_icon),
                    contentDescription = "Chat Icon",
                    modifier = Modifier.size(28.dp),
                    tint = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Chats",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.DarkGray
                )
            }


            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {

                Icon(
                    painter = painterResource(R.drawable.update_icon),
                    contentDescription = "Update Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Updates",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.DarkGray
                )
            }



            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Icon(
                    painter = painterResource(R.drawable.communities_icon),
                    contentDescription = "Communities Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Communities",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.DarkGray
                )
            }


            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.CenterVertically)
            ) {

                Icon(
                    painter = painterResource(R.drawable.outline_phone_24),
                    contentDescription = "Calling Icon",
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterHorizontally),
                    tint = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Calls",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.DarkGray
                )
            }

        }

    }
}