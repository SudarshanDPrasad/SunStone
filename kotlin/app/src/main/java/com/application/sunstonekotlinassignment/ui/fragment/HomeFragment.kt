package com.application.sunstonekotlinassignment.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.application.sunstonekotlinassignment.R
import com.application.sunstonekotlinassignment.ui.adaptor.PhotoAdaptor
import com.application.sunstonekotlinassignment.viewmodels.PhotoViewModel
import com.application.sunstonekotlinassignment.data.Status
import com.application.sunstonekotlinassignment.databinding.FragmentHomeBinding
import com.application.sunstonekotlinassignment.local.interfacemo.OnCardClicked
import com.application.sunstonekotlinassignment.local.reponse.PhotoModel
import com.application.sunstonekotlinassignment.local.reponse.SrcModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), OnCardClicked {
    private lateinit var homeBinding: FragmentHomeBinding
    val photoViewModel: PhotoViewModel by viewModels()
    lateinit var photoAdaptor: PhotoAdaptor
    var list = emptyList<PhotoModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load()
        homeBinding.btnSearch.setOnClickListener {
            loaddata(homeBinding.search.text.toString())
        }
    }

    private fun load() {
        photoViewModel.getResponseFromApi(query = "sports")
            .observe(viewLifecycleOwner, {
                when (it.status) {

                    Status.ERROR -> {
                        Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Status.SUCCESS -> {
                        list = it.data?.photoModels as ArrayList<PhotoModel>
                        setAdaptor()

                    }
                }
            })
    }
    private fun loaddata(Query: String) {
        photoViewModel.getResponseFromApi(query = Query)
            .observe(viewLifecycleOwner, {
                when (it.status) {

                    Status.ERROR -> {
                        Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Status.SUCCESS -> {
                        list = it.data?.photoModels as ArrayList<PhotoModel>
                       setAdaptor()
                    }
                }
            })
    }

    private fun setAdaptor() {
        photoAdaptor = PhotoAdaptor(list, this)
        homeBinding.mainRecyclerView.adapter = photoAdaptor
        homeBinding.mainRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onCardClicked(srcModel: SrcModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment2(
            srcModel)
        Navigation.findNavController(requireView()).navigate(action)
    }
}