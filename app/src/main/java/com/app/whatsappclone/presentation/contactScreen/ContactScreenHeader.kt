package com.app.whatsappclone.presentation.contactScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R

@Composable
@Preview(showSystemUi = true)
fun ContactScreenHeader(){

    var isSearching = remember { mutableStateOf(false)}
    var searchingText = remember { mutableStateOf("Search name or number...") }
    var isExpanded = remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        if(isSearching.value){
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(value = searchingText.value, onValueChange = {searchingText.value = it},
                    leadingIcon = {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack,"Back Button",
                            modifier = Modifier.clickable(true, onClick = {
                                isSearching.value = false
                            }))
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF0F0F0),
                        unfocusedContainerColor = Color(0xFFF0F0F0),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    shape = CircleShape,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        else{
            Row(modifier = Modifier.fillMaxWidth()
                .padding(6.dp),
                horizontalArrangement = Arrangement.Start) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back Button",
                        modifier = Modifier.align(Alignment.CenterVertically))
                }

                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text("Select contact", fontSize = 14.sp, fontWeight = FontWeight.Normal)
                    Text("399 conctacts", fontSize = 10.sp, fontWeight = FontWeight.Normal)
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = {
                        isSearching.value = true
                    }) {
                        Icon(painter = painterResource(R.drawable.search),"Search Icon",
                            modifier = Modifier.size(20.dp))
                    }
                    IconButton(onClick = {isExpanded.value = true}) {
                        Icon(painter = painterResource(R.drawable.more),"More Icon",
                            modifier = Modifier.size(20.dp))
                        DropdownMenu(expanded = isExpanded.value, onDismissRequest = {isExpanded.value = false}) {
                            DropdownMenuItem({
                                Text("Contact settings")
                            }, onClick = {})
                            DropdownMenuItem({
                                Text("Invite a friend")
                            }, onClick = {})
                            DropdownMenuItem({
                                Text("Contacts")
                            }, onClick = {})
                            DropdownMenuItem({
                                Text("Refresh")
                            }, onClick = {})
                            DropdownMenuItem({
                                Text("Help")
                            }, onClick = {})
                        }
                    }
                }
            }
            HorizontalDivider()
        }
    }
}