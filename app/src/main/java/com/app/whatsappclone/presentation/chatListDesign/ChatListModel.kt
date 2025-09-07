package com.app.whatsappclone.presentation.chatListDesign

import android.graphics.Bitmap
import android.os.Message
import com.app.whatsappclone.model.MessageModel
import java.time.LocalDateTime
import java.util.Stack


data class ChatListModel(
    val userId : String? = null,
    val image: String? = null,
    val name : String? = null,
    val isUnread : Boolean = false,
    val unreadCount : Int = 0,
    val isMuted : Boolean = false,
    val isOnline : Boolean = false,
    val messages : Stack<MessageModel>? = null
)
{
    constructor() : this(null,null,null,false,0,false,false, Stack())
}


