package com.app.whatsappclone.presentation.chatDesign

import java.time.LocalDateTime

data class MessageModel (
    val message : String,
    val time : LocalDateTime,
    val isIncoming : Boolean = true,
    val isWaiting : Boolean = true,
    val isSent : Boolean = false,
    val isDelivered : Boolean = false,
    val isRead : Boolean = false

)