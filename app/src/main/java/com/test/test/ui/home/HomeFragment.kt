package com.test.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.test.test.R
import com.test.test.databinding.FragmentHomeBinding
import com.test.test.domain.repository.service
import com.test.test.presentation.mappers.EmployMapper

class HomeFragment : Fragment()
{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val factory = HomeViewModelFactory(service)
        val homeViewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        homeViewModel.employee.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            binding.compose.setContent {
                slides(slides = it.data){employ->
                    Bundle().apply {
                        this.putParcelable("Employ", EmployMapper().fromDomainToPresentation(employ))
                    }.let { bundle ->
                        NavHostFragment.findNavController(this).navigate(R.id.action_navigation_home_to_detailFragment,bundle)
                    }
                }
            }
        } )
        homeViewModel.fetchEmployees()
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}