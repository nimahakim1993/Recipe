package com.example.recipe2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe2.R
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.databinding.FragmentHomeBinding
import com.example.recipe2.databinding.FragmentSearchBinding
import com.example.recipe2.presentation.adapter.CategoryAdapter
import com.example.recipe2.presentation.adapter.RecipeAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.presentation.viewmodel.MainViewModel
import com.example.recipe2.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{ homeViewModelFactory }

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by activityViewModels{mainViewModelFactory}

    @Inject
    lateinit var categoryAdapter: CategoryAdapter
    @Inject
    lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // Handle search query submission
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty()) {
                        tvNumRecipe.visibility = View.VISIBLE
                        tvNumCategory.visibility = View.VISIBLE
                        nested.visibility = View.VISIBLE
                        tvShowEmpty.visibility = View.GONE
                        homeViewModel.searchRecipes(newText).observe(viewLifecycleOwner){
                            binding.tvNumRecipe.text = getString(R.string.result_recipe, it.size)
                            initRecipeRecycler(it)
                        }
                        homeViewModel.searchCategories(newText).observe(viewLifecycleOwner){
                            binding.tvNumCategory.text = getString(R.string.result_category, it.size)
                            initCategoryRecycler(it)
                        }
                    }
                    else if (newText!!.isEmpty()){
                        showEmptyLayout()
                    }

                    return true
                }
            })
        }

        categoryAdapter.setOnItemClickListener { category ->
            mainViewModel.setFragmentLabel(category.title)
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToRecipeFragment(category))
        }
        recipeAdapter.setOnItemClickListener { recipe ->
            mainViewModel.setFragmentLabel(recipe.title)
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(recipe))
        }

    }

    private fun showEmptyLayout() {
        binding.apply {
            tvNumRecipe.visibility = View.GONE
            tvNumCategory.visibility = View.GONE
            nested.visibility = View.GONE
            tvShowEmpty.visibility = View.VISIBLE
        }
    }

    private fun initRecipeRecycler(recipes: List<Recipe>) {
        recipeAdapter.updateData(recipes, "")
        binding.rvRecipes.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recipeAdapter
        }
    }
    private fun initCategoryRecycler(categories: List<Category>) {
        categoryAdapter.updateData(categories)
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }
    }
}