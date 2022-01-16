package com.sudarshan.sunstoneassignment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.sudarshan.sunstoneassignment.R
import com.sudarshan.sunstoneassignment.databinding.FragmentHomeBinding
import com.sudarshan.sunstoneassignment.local.Status
import com.sudarshan.sunstoneassignment.local.interfaces.OnCardClicked
import com.sudarshan.sunstoneassignment.local.responses.PhotoModel
import com.sudarshan.sunstoneassignment.local.responses.SrcModel
import com.sudarshan.sunstoneassignment.ui.adapter.PhotoAdapter
import com.sudarshan.sunstoneassignment.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), OnCardClicked {

    private lateinit var homeBinding: FragmentHomeBinding
    private val viewModel: AppViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter
    private var emptyList = emptyList<PhotoModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getResponseFromAPI(query = "nature").observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.ERROR -> {
                    Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    emptyList = it.data?.photoModels as ArrayList<PhotoModel>
                    adapter = PhotoAdapter(emptyList, this)
                    homeBinding.recyclerViewHomeFrag.adapter = adapter
                    homeBinding.recyclerViewHomeFrag.layoutManager = GridLayoutManager(context, 2)

                }
                Status.LOADING -> {
                    Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    override fun onCardClicked(srcModel: SrcModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(srcModel)
        Navigation.findNavController(requireView()).navigate(action)
    }
}