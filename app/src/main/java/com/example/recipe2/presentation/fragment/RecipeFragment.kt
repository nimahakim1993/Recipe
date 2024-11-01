package com.example.recipe2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe2.R
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.databinding.FragmentHomeBinding
import com.example.recipe2.databinding.FragmentRecipeBinding
import com.example.recipe2.presentation.adapter.CategoryAdapter
import com.example.recipe2.presentation.adapter.RecipeAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.presentation.viewmodel.MainViewModel
import com.example.recipe2.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class RecipeFragment: Fragment() {
    private val TAG = "Recipe_TAG"
    private lateinit var binding: FragmentRecipeBinding
    private lateinit var category: Category

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{ homeViewModelFactory }
    @Inject
    lateinit var recipeAdapter: RecipeAdapter
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by activityViewModels{mainViewModelFactory}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: RecipeFragmentArgs by navArgs()
        args.let {
            this.category = it.categorySelected
        }

        mainViewModel.setFragmentLabel(category.title)

        homeViewModel.getCategoryRecipes(category.id).observe(viewLifecycleOwner) { recipes ->

            if (recipes.isNotEmpty())
                initRecycler(recipes)
            else{
                showEmptyLayout()
            }
        }

        recipeAdapter.setOnItemClickListener { recipe ->
            mainViewModel.setFragmentLabel(recipe.title)
            findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentToDetailFragment(recipe))
        }

        binding.fabAddRecipe.setOnClickListener {
            mainViewModel.setFragmentLabel(getString(R.string.create_recipe_for, category.title))
            findNavController().navigate(RecipeFragmentDirections.actionRecipeFragmentToAddRecipeFragment(category))
        }

    }

    private fun showEmptyLayout() {
        binding.rvRecipes.visibility = View.GONE
        binding.llEmpty.visibility = View.VISIBLE
    }

    private fun initRecycler(recipes: List<Recipe>) {
        binding.llEmpty.visibility = View.GONE
        recipeAdapter.updateData(recipes, category.title)
        binding.rvRecipes.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recipeAdapter
        }
    }
}