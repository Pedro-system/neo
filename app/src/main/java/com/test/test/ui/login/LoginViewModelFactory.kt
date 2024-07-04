package com.test.test.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.test.data.LoginDataSource
import com.test.test.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(val sp:SharedPreferences) : ViewModelProvider.Factory
{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
        {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    LoginDataSource(),
                    sp
                )

            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}