package com.test.test.data

import android.content.SharedPreferences
import com.test.test.data.model.Data
import com.test.test.data.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource, private val sp: SharedPreferences)
{

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init
    {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout()
    {
        user = null
        dataSource.logout()
    }

    fun login(username: String,callback : (Result<LoggedInUser>)->Unit)
    {
        // handle login
        dataSource.login(username){
            if (it is Result.Success)
            {
                setLoggedInUser(it.data)
            }
            callback(it)
        }
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser)
    {
        this.user = loggedInUser
        val data = loggedInUser.employee?:return
        sp.edit().putInt(Data.EMPLOYEE_AGE,data.employee_age)
        .putString(Data.EMPLOYEE_NAME,data.employee_name)
        .putInt(Data.EMPLOYEE_SALARY,data.employee_salary)
        .putInt(Data.ID,data.id)
        .putString(Data.PROFILE_IMAGE,data.profile_image).apply()
    }
}