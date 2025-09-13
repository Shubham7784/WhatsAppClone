package com.app.whatsappclone.presentation.contactScreen

import android.R.attr.shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R
@Composable
@Preview(showSystemUi = true)
fun ContactCard(){
    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        IconButton(onClick = {}, modifier = Modifier.size(50.dp)) {
            Image(painter = painterResource(R.drawable.profile_placeholder), contentDescription = "Profile Image", contentScale = ContentScale.Crop)
        }
        Column(Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
            Text("Name", fontSize = 16.sp, fontWeight = FontWeight.Normal, minLines = 1, maxLines = 1)
            Text("Status", fontSize = 14.sp, fontWeight = FontWeight.Normal, minLines = 1, maxLines = 1)
        }
    }
}