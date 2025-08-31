package com.app.whatsappclone.presentation.communityScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R

@Composable
@Preview(showSystemUi = true)
fun CommunityTopBar() {

    var moreExpanded = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        )
    {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Communites", fontSize = 20.sp, fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Row {
                Icon(
                    painter = painterResource(R.drawable.qr_scanner),
                    contentDescription = "QR Scanner",
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.CenterVertically)
                )

                IconButton(onClick = { moreExpanded.value = true }) {
                    Icon(
                        painter = painterResource(R.drawable.more), contentDescription = "More",
                        modifier = Modifier.size(28.dp)
                    )
                    DropdownMenu(
                        expanded = moreExpanded.value,
                        onDismissRequest = { moreExpanded.value = false },
                    ) {
                        DropdownMenuItem(text =
                        {
                            Text("Settings")
                        },
                            onClick = {})
                    }
                }
            }
        }
    }

}