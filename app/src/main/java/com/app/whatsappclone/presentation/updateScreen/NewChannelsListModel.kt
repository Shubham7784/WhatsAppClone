package com.app.whatsappclone.presentation.updateScreen

data class NewChannelsListModel(
    val image : Int,
    val name : String,
    val followers : String,
    val isVerified : Boolean = false
)