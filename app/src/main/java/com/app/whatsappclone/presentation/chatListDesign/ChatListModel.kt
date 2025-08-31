package com.app.whatsappclone.presentation.chatListDesign

import com.app.whatsappclone.presentation.chatDesign.MessageModel
import java.util.Stack


data class ChatListModel(
    val image: Int,
    val name : String,
    val time : String,
    val isUnread : Boolean,
    val unreadCount : Int,
    val isMuted : Boolean = false,
    val isOnline : Boolean = false,
    val messages : Stack<MessageModel> = Stack()
)