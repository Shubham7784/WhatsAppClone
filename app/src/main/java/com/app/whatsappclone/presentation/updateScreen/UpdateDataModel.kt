package com.app.whatsappclone.presentation.updateScreen

data class UpdateDataModel(
    val avatarRes: Int,
    val name: String,
    val time: String,
    val isNew: Boolean = true,
)
