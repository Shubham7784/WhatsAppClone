package com.app.whatsappclone.presentation.contactScreen

import android.content.Context
import android.provider.ContactsContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.app.whatsappclone.R
import com.app.whatsappclone.model.PhoneAuthUser
import com.app.whatsappclone.presentation.viewModel.BaseViewModel

@Composable
fun ContactScreen(navHostController: NavHostController, baseViewModel: BaseViewModel = BaseViewModel(),context: Context) {
    var contacts = remember {mutableStateOf(listOf<PhoneAuthUser>())}

    LaunchedEffect(Unit) {
        baseViewModel.fetchWhatsAppContacts(context) { matchedContact ->
            contacts.value = matchedContact
        }
    }
    Scaffold(
        topBar = { ContactScreenHeader() }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(true, onClick = {})
            ) {
                Badge(
                    containerColor = colorResource(R.color.light_green),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.outline_people_24),
                        "New Group",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    "New group",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(
                            Alignment.CenterVertically
                        )
                        .padding(4.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(true, onClick = {})
            ) {
                Badge(
                    containerColor = colorResource(R.color.light_green),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.add_user_icon),
                        "New Group",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Black
                    )
                }
                Text(
                    "New contact",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(
                            Alignment.CenterVertically
                        )
                        .padding(4.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(true, onClick = {})
            ) {
                Badge(
                    containerColor = colorResource(R.color.light_green),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.communities_icon),
                        "Community Icon",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    "New community",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(
                            Alignment.CenterVertically
                        )
                        .padding(4.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable(true, onClick = {})
            ) {
                Badge(
                    containerColor = colorResource(R.color.light_green),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(2.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.meta_ai_logo),
                        contentDescription = "AI logo",
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    "Chat with AIs",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .align(
                            Alignment.CenterVertically
                        )
                        .padding(4.dp)
                )
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(
                    text = "Contacts on WhatsApp",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            //Contacts to be added from server
            for (contact in contacts.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable(true, onClick = {})
                ) {

                    IconButton(onClick = {}) {
                        val profileBitMap = remember { contact.profileImage.let { baseViewModel.base64toBitmap(it) } }
                        Image(
                            painter = if(profileBitMap!=null){
                                rememberImagePainter(profileBitMap)
                            }
                            else{
                                painterResource(R.drawable.profile_placeholder)
                            },
                            contentDescription = "Profile Image",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = contact.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .align(
                                    Alignment.Start
                                )
                                .padding(4.dp)
                        )
                        Text(
                            text = contact.status,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .align(
                                    Alignment.Start
                                )
                                .padding(4.dp)
                        )
                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Invite to WhatsApp", fontSize = 14.sp, fontWeight = FontWeight.Normal)
            }
            //Contacts invite to be added
        }
    }
}
