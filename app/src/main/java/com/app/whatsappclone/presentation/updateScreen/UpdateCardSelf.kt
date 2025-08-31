package com.app.whatsappclone.presentation.updateScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.whatsappclone.R
@Composable
fun UpdateCardSelf(profilePicture : Int,onClick : () -> Unit)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 6.dp)
        .size(100.dp,160.dp), shape = RoundedCornerShape(10.dp), colors =
    CardColors(Color.LightGray, Color.LightGray, Color.LightGray, Color.LightGray),
        onClick = {onClick()}){
        Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                Box(
                    modifier = Modifier.size(64.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    // Avatar
                    Image(
                        painter = painterResource(R.drawable.hrithik_roshan),
                        contentDescription = "My Status",
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )

                    // Plus (+) icon
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF25D366)), // WhatsApp green
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Status",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Text(text = "Add status", modifier = Modifier.align(Alignment.End), color = Color.Black,
                fontWeight = FontWeight.Medium)
        }
    }
}
