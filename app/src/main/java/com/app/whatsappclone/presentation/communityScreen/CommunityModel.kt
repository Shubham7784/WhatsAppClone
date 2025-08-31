package com.app.whatsappclone.presentation.communityScreen

import androidx.collection.ObjectList
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel

data class CommunityModel(
    val communityImage : Int,
    val communityName : String,
    val announcementModel: AnnouncementModel,
    val groupModel : ObjectList<ChatListModel>
)