package com.example.compose.ui.signin

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.source.AuthRepository
import com.example.compose.interceptor.AuthInterceptor
import com.example.compose.interceptor.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val authInterceptor: AuthInterceptor,
    private val errorHandler: ErrorHandler) : ViewModel() {

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    var loading  = mutableStateOf(false)
        private set

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun auth() {
        viewModelScope.launch {
            authRepository.token().collect {
                _success.value = !it.isNullOrEmpty()
                authInterceptor.token = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.clear()
        }
    }

    fun login(username: String, password: String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                loading.value = false
                val response = authRepository.auth(username = username, password = password)
                if(response.isSuccessful) {
                    val token = response.headers()["Authorization"]
                    if(token != null) {
                        authInterceptor.token = token
                        authRepository.token(token = token)
//                        val userResponse = userRepository.user(username!!)
//                        if(userResponse.isSuccessful) {
//                            val user: User = userResponse.body()
//                            user.token = token
//                            if (userRepository.insert(user) != (-1).toLong()) {
//                                _success.postValue("Success")
//                            } else {
//                                _error.postValue("Authentification Falid")
//                            }
//                        } else {
//                            _error.postValue("Authentification Falid")
//                        }
                    } else {
                        _error.postValue("Authentification Falid")
                    }
                } else {
                    _error.postValue(errorHandler.parseError(response).message)
                }
            } catch (ex: Exception) {
                loading.value = false
                _error.postValue(ex.message)
                Log.d(AuthViewModel::class.java.name, "Ex:" + ex.message.toString())
            }
        }
    }
}