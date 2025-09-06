package com.app.whatsappclone.presentation.userRegistrationProfileScreen

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.navigation.Routes
import com.app.whatsappclone.presentation.viewModel.PhoneAuthViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlin.contracts.contract


@Composable
fun UserRegistrationProfile(
    navHostController: NavHostController,
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel()
) {

    var name = remember { mutableStateOf("") }
    var profileImageUri = remember { mutableStateOf<Uri?>(null) }
    var bitMapImage = remember { mutableStateOf<Bitmap?>(null) }


    val fireBaseAuth = Firebase.auth
    val phoneNumber = fireBaseAuth.currentUser?.phoneNumber ?: ""
    val userId = fireBaseAuth.currentUser?.uid ?: ""

    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            profileImageUri.value = uri

            uri?.let {
                bitMapImage.value = if (Build.VERSION.SDK_INT < 28) {
                    @Suppress("DEPRECIATION")
                    android.provider.MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }

        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Set up your profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_green)
        )
        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 66.dp),
            thickness = 2.dp,
            color = colorResource(R.color.dark_green)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(onClick = {
                imagePickerLauncher.launch("image/*")
            }, modifier = Modifier.size(80.dp)) {
                if (bitMapImage.value != null) {
                    Image(
                        bitMapImage.value!!.asImageBitmap(),
                        "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(
                                CircleShape
                            )
                    )
                } else if (profileImageUri.value != null) {
                    Image(
                        painter = rememberImagePainter(profileImageUri.value),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        painter = painterResource(R.drawable.profile_placeholder),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }
            }

            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Button(
            onClick = {
                phoneAuthViewModel.saveUserProfile(
                    userId,
                    name.value,
                    "Hey there ! I am using Whatsapp",
                    if(bitMapImage.value==null) null else bitMapImage.value
                )
                navHostController.navigate(Routes.HomeScreen){
                    popUpTo(Routes.UserRegistrationScreen){
                        inclusive = true
                    }
                }
            },
            colors = ButtonColors(
                containerColor = colorResource(R.color.dark_green),
                contentColor = Color.White,
                disabledContainerColor = colorResource(R.color.dark_green),
                disabledContentColor = Color.White
            ),
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = "Next")
        }
    }
}