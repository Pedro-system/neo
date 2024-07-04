package com.test.test.data

import com.test.test.data.model.Data
import com.test.test.data.model.EmployeeDomainModel
import com.test.test.data.model.LoggedInUser
import com.test.test.domain.repository.service
import retrofit2.Call
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource()
{

    fun login(username: String, callback :  (Result<LoggedInUser>)-> Unit )
    {
        try
        {
            service.employee(username.toInt())
                .enqueue(
                    object : retrofit2.Callback<EmployeeDomainModel>
                    {
                        override fun onResponse(
                            call: Call<EmployeeDomainModel>,
                            response: Response<EmployeeDomainModel>
                        )
                        {
                            callback(if (response.isSuccessful)
                            {
                                val employee = response.body() ?: EmployeeDomainModel( )
                                Result.Success(
                                    LoggedInUser(
                                        java.util.UUID.randomUUID().toString(),
                                        employee.data?.employee_name.orEmpty()
                                        ,employee.data

                                    )
                                )
                            } else
                            {
                                Result.Error(IOException("Error logging in"))
                            })
                        }

                        override fun onFailure(call: Call<EmployeeDomainModel>, t: Throwable)
                        {
                            callback( Result.Error(IOException("Error logging in")) )
                        }
                    },
                )
        }
        catch (e:HttpException)
        {
            callback ( Result.Error(IOException("Error logging in")) )
        }
    }

    fun logout()
    {
        // TODO: revoke authentication
    }
}