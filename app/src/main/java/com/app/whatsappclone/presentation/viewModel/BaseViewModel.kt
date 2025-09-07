package com.app.whatsappclone.presentation.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.telecom.Call
import android.util.Log
import androidx.lifecycle.ViewModel
import com.app.whatsappclone.model.MessageModel
import com.app.whatsappclone.presentation.chatListDesign.ChatListModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import okio.IOException
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Stack
import kotlin.io.encoding.Base64

class BaseViewModel : ViewModel() {
    fun searchUserByPhnoneNumber(phoneNumber: String, callback: (ChatListModel?) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            Log.e("Base View Model", "User is not Authenticated")
            callback(null)
            return
        }

        val databaseReference = FirebaseDatabase.getInstance().getReference("users")
        databaseReference.orderByChild("phoneNumber").equalTo(phoneNumber)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val user = snapshot.children.first().getValue(ChatListModel::class.java)
                        callback(user)
                    } else
                        callback(null)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e(
                        "BaseViewModel",
                        "Error fetching User : ${error.message} , Details : ${error.details}"
                    )
                    callback(null)
                }
            })
    }

    fun getChatForUser(userId: String, callback: (List<ChatListModel>) -> Unit) {
        val chatRef = FirebaseDatabase.getInstance().getReference("users/$userId/chats")
        chatRef.orderByChild("userId").equalTo(userId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val chatList = mutableListOf<ChatListModel>()

                    for (childSnapshot in snapshot.children) {
                        val chat = childSnapshot.getValue(ChatListModel::class.java)

                        if (chat != null)
                            chatList.add(chat)
                    }
                    callback(chatList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("BaseViewModel", "Error fetching user chats : ${error.message}")
                    callback(emptyList())
                }
            })
    }

    private val _chatList = MutableStateFlow<List<ChatListModel>>(emptyList())

    val chatList = _chatList.asStateFlow()

    fun loadChatState() {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid

        if (currentUserId != null) {
            val chatRef = FirebaseDatabase.getInstance().getReference("chats")

            chatRef.orderByChild("userId").equalTo(currentUserId)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val chatList = mutableListOf<ChatListModel>()

                        for (childSnapshot in snapshot.children) {
                            val chat = childSnapshot.getValue(ChatListModel::class.java)
                            if (chat != null)
                                chatList.add(chat)
                        }
                        _chatList.value = chatList
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("BaseViewModel", "Error fetching user chats ${error.message}")
                    }
                })
        }
    }


    fun addChat(newChat: ChatListModel) {
        val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
        if (currentUserId != null) {
            val newChatRef = FirebaseDatabase.getInstance().getReference("chats").push()
            val chatWithUser = newChat.copy(currentUserId)
            newChatRef.setValue(chatWithUser).addOnSuccessListener {
                Log.d("BaseViewModel", "Chat Added Successfully to firebase")
            }
                .addOnFailureListener { exception ->
                    Log.e("BaseViewModel", "Failed to add chat : ${exception.message}")
                }
        } else {
            Log.e("BaseViewModel", "No User is Authenticated")
        }
    }

    private val databaseReference = FirebaseDatabase.getInstance().reference

    fun sendMessage(senderPhoneNumber: String, receiverPhoneNumber: String, message: String) {
        val messageId = databaseReference.push().key ?: return


        databaseReference.child("messages")
            .child(senderPhoneNumber)
            .child(receiverPhoneNumber)
            .setValue(
                MessageModel(
                    senderPhoneNumber,
                    message,
                    LocalDateTime.now(),
                    false,
                    false,
                    true,
                    true,
                    false
                )
            )

        databaseReference.child("messages")
            .child(receiverPhoneNumber)
            .child(senderPhoneNumber)
            .setValue(
                MessageModel(
                    senderPhoneNumber,
                    message,
                    LocalDateTime.now(),
                    true
                )
            )
    }

    fun getMessage(
        senderPhoneNumber: String,
        receiverPhoneNumber: String,
        onNewMessage: (MessageModel) -> Unit
    ) {
        val messageRef = databaseReference.child("messages")
            .child(senderPhoneNumber)
            .child(receiverPhoneNumber)

        messageRef.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(MessageModel::class.java)

                if (message != null) {
                    onNewMessage(message)
                }
            }

            override fun onChildChanged(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(
                snapshot: DataSnapshot,
                previousChildName: String?
            ) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun fetchLastMessageFromChat(senderPhoneNumber: String,receiverPhoneNumber: String,onLastMessageFetched:(String,String)-> Unit){
        val chatRef = FirebaseDatabase.getInstance().reference
            .child("messages")
            .child(senderPhoneNumber)
            .child(receiverPhoneNumber)

        chatRef.orderByChild("time").limitToLast(1)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists())
                    {
                        val lastMessage = snapshot.children.firstOrNull()?.child("message")?.value as? String
                        val timeStamp = snapshot.children.firstOrNull()?.child("time")?.value as? String
                        onLastMessageFetched(lastMessage?:"No message",timeStamp?:"--:--")
                    }
                    else
                    {
                        onLastMessageFetched("No message","--:--")
                    }
                }

                override fun onCancelled(error: DatabaseError){
                    onLastMessageFetched("No message","--:--")
                    
                }
            })
    }

    fun loadChatList(
        currentUserPhoneNumber :String,
        onChatListLoaded: (List<ChatListModel>) -> Unit
    )
    {
        val chatList = mutableListOf<ChatListModel>()
        val chatRef = FirebaseDatabase.getInstance().reference
            .child("chats")
            .child(currentUserPhoneNumber)

        chatRef.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    snapshot.children.forEach { child ->
                        val phoneNumber = child?.key?:return@forEach
                        val name = child.child("name").value as? String?:return@forEach
                        val image = child.child("image").value as? String?:return@forEach


                        val profileImageBitmap = decodeBase64ToBitmap(image)

                        fetchLastMessageFromChat(currentUserPhoneNumber,phoneNumber,{
                            lastMessage , time ->
                            chatList.add(
                                ChatListModel(
                                    name = name,
                                    image = image,
                                )
                            )

                            if(chatList.size == snapshot.childrenCount.toInt())
                            {
                                onChatListLoaded(chatList)
                            }
                            else
                            {
                                onChatListLoaded(emptyList())
                            }
                        })

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                onChatListLoaded(emptyList())
            }
        })
    }


    private fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        return try{
            val decodedBytes = android.util.Base64.decode(base64String,android.util.Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.size)
        }
        catch (e: IOException)
        {
            null
        }
    }

    fun base64toBitmap(base64String : String):Bitmap?{
        return try{
            val decodedBytes = android.util.Base64.decode(base64String,android.util.Base64.DEFAULT)
            val inputStream : InputStream = ByteArrayInputStream(decodedBytes)
            BitmapFactory.decodeStream(inputStream)
        }
        catch (e: IOException)
        {
            null
        }
    }
}