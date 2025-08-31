package com.app.whatsappclone.presentation.updateScreen

import androidx.collection.objectListOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.app.whatsappclone.presentation.homeScreen.UpdateSearchBar

@Composable
@Preview(showSystemUi = true)
fun UpdateScreenTopBar()
{
    var isSearching = remember { mutableStateOf(false) }
    var searchText = remember { mutableStateOf("Search...") }
    var moreExpanded = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth()){
        if(isSearching.value)
        {
            UpdateSearchBar(query = searchText.value, onQueryChange = {searchText.value = it},isSearching)
        }
        else
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(text = "Updates", fontWeight = FontWeight.Medium, fontSize = 20.sp, modifier = Modifier.align(
                    Alignment.CenterVertically))


                Row{
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(38.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.qr_scanner),
                            contentDescription = "QR Scanner",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = {isSearching.value = true},
                        modifier = Modifier
                            .size(38.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.search),
                            contentDescription = "Search" ,
                            modifier = Modifier.size(32.dp)

                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        onClick = {moreExpanded.value = true},
                        modifier = Modifier
                            .size(38.dp)
                            .align(Alignment.Bottom)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = "More",
                            modifier = Modifier.size(32.dp)
                        )
                        DropdownMenu(expanded = moreExpanded.value, onDismissRequest = {moreExpanded.value = false}) {
                            objectListOf<String>("Create channel","Status privacy","Starred","Settings").forEach { option ->
                                DropdownMenuItem(text = {
                                    Text(text = option)
                                }, onClick = {})
                            }
                        }
                    }
                }
            }

        }
    }
}
