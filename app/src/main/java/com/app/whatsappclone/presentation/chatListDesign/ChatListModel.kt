package com.app.whatsappclone.presentation.chatListDesign

import com.app.whatsappclone.model.MessageModel
import java.util.Stack


data class ChatListModel(
    val userId : String? = null,
    val image: Int? = null,
    val name : String? = null,
    val time : String? = null,
    val isUnread : Boolean = false,
    val unreadCount : Int = 0,
    val isMuted : Boolean = false,
    val isOnline : Boolean = false,
    val messages : Stack<MessageModel>? = null
)
{
    constructor() : this(null,null,null,null,false,0,false,false,null)
}


