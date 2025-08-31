package com.app.whatsappclone.presentation.chatDesign

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import com.app.whatsappclone.R
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview(showSystemUi = true)
fun ChatBottomSection() {
    var message = remember { mutableStateOf("Message") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp).
        background(Color.Transparent)
    ) {
        TextField(
            placeholder = {
                Text(text = "Message")
            },
            value = message.value, onValueChange = { message.value = it },
            trailingIcon = {
                Row {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_attach_file_24),
                            contentDescription = "Attach File"
                        )
                    }
                    IconButton(onClick = {}) {
                        Badge(
                            modifier = Modifier
                                .size(30.dp)
                                .border(
                                    BorderStroke(2.dp, color = Color.Gray),
                                    shape = CircleShape
                                ),
                            containerColor = Color.Transparent
                        )
                        {
                            Icon(painter = painterResource(R.drawable.rupee_icon), contentDescription = "Rupee Icon",
                                modifier = Modifier.size(18.dp))
                        }
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.camera),
                            contentDescription = "Camera Icon",
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            },
            leadingIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.outline_emoji_emotions_24),
                        contentDescription = "Emoji Icon"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Gray,
                unfocusedTextColor = Color.Gray
            ),
            shape = CircleShape,
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
            modifier = Modifier
                .padding(start = 6.dp, end = 6.dp)

        )
        IconButton(onClick = {}, modifier = Modifier.align(Alignment.CenterVertically).size(50.dp),
            colors = IconButtonColors(containerColor = colorResource(R.color.light_green),
                Color.Black,colorResource(R.color.light_green), Color.Black)) {
            Icon(
                painter = painterResource(R.drawable.baseline_mic_24),
                contentDescription = "Mic Icon",
                modifier = Modifier.size(28.dp)
            )
        }
    }
}