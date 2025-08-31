package com.app.whatsappclone.presentation.updateScreen

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R

@Composable
fun NewChannelDesign(newChannelsListModel: NewChannelsListModel) {
    Row(
        modifier = Modifier.padding(10.dp, top = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Image(
            painter = painterResource(newChannelsListModel.image), contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row {
                    Text(
                        text = newChannelsListModel.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    if(newChannelsListModel.isVerified){
                        Image(painter = painterResource(R.drawable.verified_icon), contentDescription = "Verified Icon",
                            modifier = Modifier.size(20.dp))
                    }
                }

                Button(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .height(40.dp),
                    colors = ButtonColors(
                        containerColor = Color(red = 216, green = 253, blue = 210),
                        contentColor = colorResource(R.color.light_green),
                        disabledContainerColor =
                        Color(red = 216, green = 253, blue = 210),
                        disabledContentColor = colorResource(R.color.light_green)
                    )
                ) {
                    Text(text = "Follow")
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = newChannelsListModel.followers,
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}