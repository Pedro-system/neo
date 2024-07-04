package com.test.test.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.test.test.MainActivity
import com.test.test.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity()
{

    init
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    private val sp by lazy {  getSharedPreferences("login_shared", MODE_PRIVATE) }
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if( sp.getBoolean("login",false) )
        {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val username = binding.username
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(sp))[LoginViewModel::class.java]

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid
            if(login.isEnabled )
            {
                login.setOnClickListener {
                    loginViewModel.login(username.text.toString(),"")
                }
            }

            if (loginState.usernameError != null)
            {
                username.error = getString(loginState.usernameError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null)
            {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null)
            {
                updateUiWithUser(loginResult.success)
                finish()
            }
            setResult(Activity.RESULT_OK)

        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),""
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        sp.edit().putBoolean("login",true).apply()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showLoginFailed(@StringRes errorString: Int)
    {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit)
{
    this.addTextChangedListener(object : TextWatcher
                                {
                                    override fun afterTextChanged(editable: Editable?)
                                    {
                                        afterTextChanged.invoke(editable.toString())
                                    }

                                    override fun beforeTextChanged(
                                        s: CharSequence,
                                        start: Int,
                                        count: Int,
                                        after: Int
                                    )
                                    {
                                    }

                                    override fun onTextChanged(
                                        s: CharSequence,
                                        start: Int,
                                        before: Int,
                                        count: Int
                                    )
                                    {
                                    }
                                })
}