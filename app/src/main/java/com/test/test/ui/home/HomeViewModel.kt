package com.test.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.test.test.data.model.EmployeesDomainModel
import com.test.test.domain.repository.EmployeeService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.IllegalArgumentException

class HomeViewModelFactory(val service: EmployeeService) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T
    {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java))
        {
            return HomeViewModel(service) as T
        }
        else{
            throw IllegalArgumentException()
        }
    }
}

class HomeViewModel(private val service : EmployeeService) : ViewModel()
{
    private val _employee = MutableLiveData<EmployeesDomainModel>()
    val employee : LiveData<EmployeesDomainModel>
        get() = _employee



    fun fetchEmployees() {
        val response = service.employees().enqueue( object : retrofit2.Callback<EmployeesDomainModel>{
            override fun onResponse(
                call: Call<EmployeesDomainModel>,
                response: Response<EmployeesDomainModel>
            )
            {
                if(response.isSuccessful)
                {
                    _employee.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<EmployeesDomainModel>, t: Throwable)
            {
            }
        }

        )
    }

}