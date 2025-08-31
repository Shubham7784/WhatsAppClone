package com.app.whatsappclone.presentation.communityScreen

data class AnnouncementModel(
    val message : String,
    val time : String,
    val isMuted : Boolean,
    val isUnread : Boolean,
)