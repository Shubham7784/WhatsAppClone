package com.app.whatsappclone.presentation.userRegistrationScreen

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.collection.arrayMapOf
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.whatsappclone.presentation.viewModel.AuthState
import com.app.whatsappclone.presentation.viewModel.PhoneAuthViewModel
import com.app.whatsappclone.R
import com.app.whatsappclone.presentation.navigation.Routes

@Composable
fun UserRegistrationScreen(
    navHostController: NavHostController,
    phoneAuthViewModel: PhoneAuthViewModel = hiltViewModel()
) {
    var expanded = remember { mutableStateOf(false) }
    var selectedCountry = remember { mutableStateOf("India") }
    var countryCode = remember { mutableStateOf("+91") }
    var phoneNumber = remember { mutableStateOf("") }

    val phoneAuthViewModel = hiltViewModel<PhoneAuthViewModel>()
    val authState = phoneAuthViewModel.authState.collectAsState()
    val context = LocalContext.current
    val activity = LocalActivity.current as Activity

    var otp = remember { mutableStateOf("") }
    var verificationId = remember { mutableStateOf<String?>(null) }

    val countryCodeMap = arrayMapOf<String, String>()
    countryCodeMap.put("India","+91")
    countryCodeMap.put("United States", "+1")
    countryCodeMap.put("United Kingdom", "+44")
    countryCodeMap.put("Canada", "+1")
    countryCodeMap.put("Australia", "+61")
    countryCodeMap.put("Germany", "+49")
    countryCodeMap.put("France", "+33")
    countryCodeMap.put("Japan", "+81")
    countryCodeMap.put("China", "+86")
    countryCodeMap.put("Brazil", "+55")
    countryCodeMap.put("South Africa", "+27")
    countryCodeMap.put("Russia", "+7")
    countryCodeMap.put("Mexico", "+52")
    countryCodeMap.put("Italy", "+39")
    countryCodeMap.put("Spain", "+34")
    countryCodeMap.put("Singapore", "+65")
    countryCodeMap.put("United Arab Emirates", "+971")
    countryCodeMap.put("Saudi Arabia", "+966")
    countryCodeMap.put("Nepal", "+977")
    countryCodeMap.put("Pakistan", "+92")
    countryCodeMap.put("Bangladesh", "+880")
    countryCodeMap.put("Sri Lanka", "+94")
    countryCodeMap.put("New Zealand", "+64")
    countryCodeMap.put("Thailand", "+66")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            when (authState.value) {
                is AuthState.Ideal -> {
                    Text(
                        text = "Enter your Phone Number", fontSize = 20.sp, modifier = Modifier.padding(16.dp),
                        color = colorResource(id = R.color.dark_green),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(text = "WhatsApp will need to verify your phone number", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "What's my number?",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.dark_green)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { expanded.value = true }, modifier = Modifier.fillMaxWidth()) {
                        Box(modifier = Modifier.width(230.dp)) {
                            Text(
                                text = selectedCountry.value,
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Icon(
                                imageVector = Icons.Default.ArrowDropDown, contentDescription = null,
                                modifier = Modifier.align(Alignment.CenterEnd),
                                tint = colorResource(id = R.color.light_green)
                            )
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 66.dp),
                        thickness = 2.dp,
                        color = colorResource(R.color.dark_green)
                    )

                    DropdownMenu(
                        expanded = expanded.value, onDismissRequest = { expanded.value = false },
                        modifier = Modifier
                            .width(230.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        for (string in countryCodeMap.keys) {
                            DropdownMenuItem(text = {
                                Text(string)
                            }, onClick = {
                                selectedCountry.value = string
                                countryCode.value = countryCodeMap.get(string).toString()
                                expanded.value = false
                            })
                        }
                    }
                    if (verificationId.value == null) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            TextField(
                                value = countryCode.value,
                                onValueChange = { countryCode.value = it },
                                modifier = Modifier.width(70.dp),
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = colorResource(R.color.light_green),
                                )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            TextField(
                                value = phoneNumber.value,
                                onValueChange = { phoneNumber.value = it },
                                placeholder = { Text(text = "Phone Number") },
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    focusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = colorResource(R.color.light_green)
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = {
                                    if (phoneNumber.value.isNotEmpty()) {
                                        val fullPhoneNumber =
                                            "${countryCode.value}${phoneNumber.value}"
                                        phoneAuthViewModel.sendVerificationCode(
                                            fullPhoneNumber,
                                            activity
                                        )
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please Enter a Valid Phone Number",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                shape = RoundedCornerShape(6.dp),
                                colors = ButtonDefaults.buttonColors(
                                    colorResource(R.color.dark_green)
                                )
                            ) {
                                Text(text = "Send OTP")
                            }
                        }
                    }
                }

                is AuthState.Loading -> {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(color = colorResource(R.color.dark_green), modifier = Modifier.align(Alignment.CenterHorizontally))
                }

                is AuthState.CodeSent -> {

                    Text(
                        text = "Verifying Your Phone Number", fontSize = 20.sp, modifier = Modifier.padding(16.dp),
                        color = colorResource(id = R.color.dark_green),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(text = "Waiting to automatically detect an SMS sent to ", fontSize = 14.sp)
                    Text(text = countryCode.value+phoneNumber.value, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Wrong number?",
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.dark_green)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    verificationId.value = (authState.value as AuthState.CodeSent).verificationId

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "Enter OTP",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.dark_green)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextField(
                        value = otp.value,
                        onValueChange = { otp.value = it },
                        placeholder = { Text(text = "Enter OTP") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = colorResource(R.color.light_green)
                        )

                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            if (otp.value.isNotEmpty() && verificationId.value != null) {
                                phoneAuthViewModel.verifyCode(otp.value, context)
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please Enter a Valid OTP",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        },
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.dark_green))
                    )
                    {
                        Text(text = "Verify OTP")
                    }
                }

                is AuthState.Success -> {
                    Log.d("Phone Auth", "Login Successful")
                    phoneAuthViewModel.resetAuthState()
                    navHostController.navigate(Routes.UserProfileScreen) {
                        popUpTo<Routes.UserRegistrationScreen> {
                            inclusive = true
                        }
                    }
                }

                is AuthState.Error -> {
                    Toast.makeText(
                        context,
                        (authState.value as AuthState.Error).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}