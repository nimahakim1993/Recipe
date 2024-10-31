package com.example.recipe2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.databinding.FragmentHomeBinding
import com.example.recipe2.databinding.FragmentRecipeBinding
import com.example.recipe2.presentation.adapter.CategoryAdapter
import com.example.recipe2.presentation.adapter.RecipeAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
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

//        homeViewModel.getAllRecipe().observe(viewLifecycleOwner){recipes ->
//            Toast.makeText(requireContext(), "${recipes.size}", Toast.LENGTH_SHORT).show()
//        }


            homeViewModel.getCategoryRecipes(category.id).observe(viewLifecycleOwner) { recipes ->

//                Toast.makeText(requireContext(), "${recipes.size}", Toast.LENGTH_SHORT).show()
                if (recipes.isNotEmpty())
                    initRecycler(recipes)



            }

        recipeAdapter.setOnItemClickListener { recipeId ->

        }

    }

    private fun initRecycler(recipes: List<Recipe>) {
        recipeAdapter.updateData(recipes, category.title)
        binding.rvRecipes.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = recipeAdapter
        }
    }
}