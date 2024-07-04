package com.test.test.domain.repository

import com.test.test.data.model.EmployeeDomainModel
import com.test.test.data.model.EmployeesDomainModel
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val client by lazy {
    OkHttpClient
        .Builder()
        .build()
}

private val retrofit by lazy {
    Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://dummy.restapiexample.com/api/v1/")
        .build()
}
//"employee?noofRecords=10&idStarts=1001"

val service: EmployeeService by lazy { retrofit.create(EmployeeService::class.java) }

interface EmployeeService
{
    @GET("employee/{id}")
    fun employee(@Path("id") id : Int  ) : Call<EmployeeDomainModel>
    @GET("employees")
    fun employees(  ) : Call<EmployeesDomainModel>
}

