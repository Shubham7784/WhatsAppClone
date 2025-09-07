package com.app.whatsappclone.presentation.homeScreen

import android.widget.Toast
import androidx.collection.objectListOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.bottomNavigation.BottomNavigation
import com.app.whatsappclone.presentation.chatDesign.ChatScreen
import com.app.whatsappclone.model.MessageModel
import com.app.whatsappclone.presentation.chatListDesign.ChatDesign
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel
import com.app.whatsappclone.presentation.navigation.Routes
import com.app.whatsappclone.presentation.viewModel.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime
import java.util.Stack

@Composable
fun HomeScreen(navController: NavHostController,homeBaseViewModel: BaseViewModel = BaseViewModel()) {

    var showPopup = remember { mutableStateOf(false) }
    val chatData = homeBaseViewModel.chatList.collectAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid

    if(userId!=null)
    {
        LaunchedEffect(Unit) {
            homeBaseViewModel.getChatForUser(userId){ chats->


            }
        }
    }

    var showMenu = remember { mutableStateOf(false) }

    var searchText = remember { mutableStateOf("Ask Meta AI or Search") }


    var moreSelectedOption = remember { mutableStateOf("") }
    Scaffold(
        floatingActionButton = {
            Column {
                FloatingActionButton(
                    onClick = {},
                    modifier = Modifier
                        .size(55.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(R.drawable.meta_ai_logo),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                FloatingActionButton(
                    onClick = {},
                    containerColor = colorResource(R.color.light_green),
                    modifier = Modifier.size(65.dp)
                )
                {
                    Icon(
                        painter = painterResource(R.drawable.chat_icon), contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp),
                        tint = colorResource(R.color.white)
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation(navController)
        }
    )
    {
        Column(modifier = Modifier.padding(it)) {
            Box(modifier = Modifier.fillMaxWidth())
            {
                Text(
                    text = "WhatsApp", fontSize = 26.sp,
                    color = colorResource(R.color.light_green),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .align(Alignment.CenterStart)
                )

                Row(modifier = Modifier.align(Alignment.CenterEnd)) {

                    Icon(
                        painter = painterResource(R.drawable.qr_scanner), contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(
                        painter = painterResource(R.drawable.camera), contentDescription = null,
                        modifier = Modifier.size(26.dp)
                    )

                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { showMenu.value = true },
                        modifier = Modifier.size(26.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = "More"
                        )
                        DropdownMenu(
                            expanded = showMenu.value,
                            onDismissRequest = { showMenu.value = false }) {
                            DropdownMenuItem(text = {
                                Text("New Group")
                            },
                                onClick = {
                                    showMenu.value = false
                                }
                            )
                            DropdownMenuItem(text = {
                                Text("New community")
                            },
                                onClick = {
                                    showMenu.value = false
                                }
                            )
                            DropdownMenuItem(text = {
                                Text("New broadcast")
                            },
                                onClick = {
                                    showMenu.value = false
                                }
                            )
                            DropdownMenuItem(text = {
                                Text("Linked devices")
                            },
                                onClick = {
                                    showMenu.value = false
                                }
                            )
                            DropdownMenuItem(text = {
                                Text("Starred")
                            },
                                onClick = {
                                    showMenu.value = false
                                }
                            )
                            DropdownMenuItem(text = {
                                Text("Payments")
                            },
                                onClick = {
                                    showMenu.value = false
                                }
                            )
                            DropdownMenuItem(text = {
                                Text("Settngs")
                            },
                                onClick = {
                                    showMenu.value = false
                                    navController.navigate(Routes.SettingScreen){
                                        popUpTo(Routes.HomeScreen){
                                            inclusive = true
                                        }
                                    }
                                }
                            )
                        }
                    }


                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))

            Column {
                WhatsAppSearchBar(
                    query = searchText.value,
                    onQueryChange = { searchText.value = it }
                )
            }

            LazyColumn {
                items(chatData.value) { chat ->
                    ChatDesign(chatListModel = chat, onClick = {
                        navController.navigate(Routes.ChatScreen)
                    })
                }
            }

        }
    }
}