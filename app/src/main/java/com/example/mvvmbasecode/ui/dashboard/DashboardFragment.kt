package com.example.mvvmbasecode.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmbasecode.R
import com.example.mvvmbasecode.base.BaseFragment
import com.example.mvvmbasecode.databinding.FragmentDashboardBinding
import com.example.mvvmbasecode.network.APIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private lateinit var dashboardAdapter: DashboardAdapter

    private val viewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        observer()
    }

    private fun init() {
        viewModel.getUserData()

        dashboardAdapter = DashboardAdapter(arrayListOf())
        binding.rvUserData.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvUserData.adapter = dashboardAdapter
    }

    private fun observer() {
        lifecycleScope.launch {
            viewModel.stateUserData.collect { state ->
                when (state) {
                    is APIState.Loading -> {
                        showProgress()
                    }

                    is APIState.Error -> {
                        hideProgress()
                    }

                    is APIState.Success -> {
                        hideProgress()
                        Log.e("kopads", state.response.toString())
                        val userData = state.response
                        dashboardAdapter.addListOfData(userData)

                    }

                    is APIState.Idle -> {}
                }

            }
        }
    }

    private fun setListener() {

    }

}