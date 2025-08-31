package com.app.whatsappclone.presentation.callsScreen

import java.time.LocalDateTime

data class CallDataModel(
    val name : String,
    val number : String,
    val isMultiple : Boolean,
    val dateTime : LocalDateTime,
    val isOutgoing : Boolean,
    val isMissed : Boolean,
    val isVideo : Boolean,
    val multipleCount : Int
    )