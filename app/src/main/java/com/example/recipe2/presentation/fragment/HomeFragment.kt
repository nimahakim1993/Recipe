package com.example.recipe2.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe2.data.entity.Category
import com.example.recipe2.databinding.FragmentHomeBinding
import com.example.recipe2.presentation.adapter.CategoryAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.presentation.viewmodel.MainViewModel
import com.example.recipe2.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val TAG = "HOME_TAG"
@AndroidEntryPoint
class HomeFragment: Fragment() {
    private lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{ homeViewModelFactory }
    @Inject
    lateinit var categoryAdapter: CategoryAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by activityViewModels{mainViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getAllCategories().observe(viewLifecycleOwner){}
        Handler(Looper.getMainLooper()).postDelayed({
            homeViewModel.getAllCategories().observe(viewLifecycleOwner) { categories ->
                if (categories.isNotEmpty())
                    initRecycler(categories)
            }
        }, 100)


        binding.fabAddCategory.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddCategoryFragment())
        }

        categoryAdapter.setOnItemClickListener { category ->
            mainViewModel.setFragmentLabel(category.title)
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToRecipeFragment(category))
        }

    }

    private fun initRecycler(categories: List<Category>) {
        categoryAdapter.updateData(categories)
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }
    }
}