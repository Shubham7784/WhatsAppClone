package com.app.whatsappclone.presentation.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.whatsappclone.R

@Composable
fun WhatsAppSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        TextField(
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search…") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        )
    }
}

@Composable
fun UpdateSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    isSearching : MutableState<Boolean>,
    modifier: Modifier = Modifier
)
{
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        TextField(
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text("Search…") },
            leadingIcon = {
                IconButton(onClick = {isSearching.value = false}) {
                    Icon(painter = painterResource(R.drawable.baseline_arrow_back_24), contentDescription = "Search Arrow Back")
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F0F0),
                unfocusedContainerColor = Color(0xFFF0F0F0),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(50.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
        )
    }
}