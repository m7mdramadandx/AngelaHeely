package com.example.angelaheely.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.angelaheely.domain.model.Response
import com.example.angelaheely.domain.model.User
import com.example.angelaheely.domain.usecases.DeleteCurrentUserUseCase
import com.example.angelaheely.domain.usecases.GetCurrentUserUseCase
import com.example.angelaheely.domain.usecases.GetMedicinesUseCase
import com.example.angelaheely.domain.usecases.SetUserUseCase
import com.example.angelaheely.utils.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val setUserUseCase: SetUserUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val deleteCurrentUserUseCase: DeleteCurrentUserUseCase,
    private val getMedicinesUseCase: GetMedicinesUseCase,
) : ViewModel() {

    init {
        getUser()
        getMedicines()
    }

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    var screenState by mutableStateOf(ScreenState<Response>())
//    private val _medicines = mutableStateOf<ScreenState<Response>>(ScreenState())
//    val medicines: State<ScreenState<Response>> = _medicines

    val username by mutableStateOf("")
    val password by mutableStateOf("")

    val currentTime = Calendar.getInstance().time

    fun setUser(username: String, password: String) {
        val user = User(username, password)
        setUserUseCase.execute(user)
    }

    fun deleteCurrentUser() {
        deleteCurrentUserUseCase.execute()
    }

    fun getUser() {

        viewModelScope.launch {
            getCurrentUserUseCase.execute()
                .collectLatest { localUser ->
                    if (localUser == null) {
                        _user.value = null
                    } else {
                        _user.value = localUser
                    }
                }
        }
    }

    private fun getMedicines() {
        viewModelScope.launch {
            val response = getMedicinesUseCase.execute()
            if (response.data() != null) {
                screenState = screenState.copy(isLoading = false, item = response.data())
                println(screenState.item)
            } else {
                screenState = screenState.copy(isLoading = false, error = "Something went wrong")

            }
        }
    }

    fun getCurrentTime(): String {
        val currentTime = Calendar.getInstance().time
        val timeFormat = java.text.SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())
        return timeFormat.format(currentTime)
    }
}
