package com.app.whatsappclone.presentation.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import com.app.whatsappclone.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.whatsappclone.presentation.navigation.Routes
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navHostController: NavHostController)
{
    LaunchedEffect(Unit) {
        delay(2000)
        navHostController.navigate(Routes.WelcomeScrenn)
        {
            popUpTo(Routes.SplashScreen)
            {
                inclusive = true
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()){
        Image(modifier = Modifier.size(80.dp)
            .align(Alignment.Center),
            painter = painterResource(id = R.drawable.whatsapp_icon), contentDescription = null
        )

        Column(modifier = Modifier.align(Alignment.BottomCenter)
            .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "From", fontSize = 12.sp, fontWeight = FontWeight.Bold)

            Row(horizontalArrangement = Arrangement.Center) {
                Icon(modifier = Modifier.size(26.dp)
                    .padding(2.dp)
                    ,painter = painterResource(id = R.drawable.meta),
                    contentDescription = null,
                    tint = colorResource(R.color.light_green)
                )
                Text(text = "Meta", fontWeight = FontWeight.Bold , fontSize = 18.sp, modifier = Modifier.align(
                    Alignment.CenterVertically))
            }
        }
    }
}
