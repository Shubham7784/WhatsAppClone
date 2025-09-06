package com.app.whatsappclone.model

import java.time.LocalDateTime

data class MessageModel (
    val senderPhoneNumber: String = "",
    val message : String = "",
    val time : Long = 0L,
    val isIncoming : Boolean = true,
    val isWaiting : Boolean = true,
    val isSent : Boolean = false,
    val isDelivered : Boolean = false,
    val isRead : Boolean = false

)