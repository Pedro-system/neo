package com.test.test.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.test.test.databinding.FragmentDetailBinding
import com.test.test.presentation.model.EmployPresentationModel

class DetailFragment : Fragment()
{

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        return FragmentDetailBinding.inflate(inflater,container,false).also { binding=it }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        val employ = requireArguments().getParcelable("Employ") ?: EmployPresentationModel()
        binding.name.text = employ.name
        "${employ.age} años".let {
            binding.birthday.text = it
        }
        binding.salary.text = employ.salary.toString()
        binding.email.text = employ.email
        binding.address.text = employ.address
        binding.phone .text = employ.contactNumber
        Glide.with(requireContext()).load(employ.imageUrl.also{ println("loading $it") })
            .signature(ObjectKey(System.currentTimeMillis().hashCode()))
            .into(binding.photo)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

}