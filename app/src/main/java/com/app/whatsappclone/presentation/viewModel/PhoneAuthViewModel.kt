package com.app.whatsappclone.presentation.viewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.compose.ui.text.capitalize
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.app.whatsappclone.WhatsAppCloneApplication
import com.app.whatsappclone.model.PhoneAuthUser
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase
) : ViewModel() {
    private val _authState = MutableStateFlow<AuthState>(AuthState.Ideal)
    val authState = _authState.asStateFlow()

    private val userRef = database.reference.child("users")

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        _authState.value = AuthState.Loading
        val option = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("Phone Auth", "onCodeSent triggered , verification ID : $id")
                _authState.value = AuthState.CodeSent(id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithCredential(credential, context = activity)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("Phone Auth", "Verification Failed : ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification Failed")
            }
        }

        val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L , TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(option)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential, context: Context){
        _authState.value = AuthState.Loading

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener{
                task ->
                if (task.isSuccessful)
                {
                    val user = firebaseAuth.currentUser
                    val phoneAuthUser = PhoneAuthUser(
                        userId = user?.uid ?: "",
                        phoneNumber = user?.phoneNumber ?: "",
                    )

                    markUserAsSignedIn(context)
                    _authState.value = AuthState.Success(phoneAuthUser)

                    fetchUserProfile(user?.uid ?: "")
                }
                else
                {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign In Failed")
                }
            }

    }
    private fun markUserAsSignedIn(context: Context) {
        val sharedPreference = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreference.edit().putBoolean("isSignedIn",true).apply()
    }

    private fun fetchUserProfile(userId: String) {
        val userRef = database.reference.child(userId)
        userRef.get().addOnSuccessListener{
            snapshot ->
            if(snapshot.exists())
            {
                val userProfile = snapshot.getValue(PhoneAuthUser::class.java)

                if(userProfile !=null)
                {
                    _authState.value = AuthState.Success(userProfile)
                }
            }
        }
            .addOnFailureListener{
                _authState.value = AuthState.Error("Failed to fetch user profile")
            }
    }

    fun verifyCode(otp : String , context : Context)
    {
        val currentAuthState = _authState.value

        if(currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty())
        {
            _authState.value = AuthState.Error("Verification not started or invalid ID")
            return
        }

        val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationId,otp)
        signInWithCredential(credential,context)
    }

}


sealed class AuthState {
    object Ideal : AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Success(val user: PhoneAuthUser) : AuthState()
    data class Error(val message: String) : AuthState()

}