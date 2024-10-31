package com.example.recipe2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.data.model.Ingredient
import com.example.recipe2.databinding.FragmentDetailBinding
import com.example.recipe2.presentation.adapter.IngredientAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class DetailFragment: Fragment() {
    private val TAG = "Detail_TAG"
    private lateinit var binding: FragmentDetailBinding
    private lateinit var recipe: Recipe

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{ homeViewModelFactory }
    @Inject
    lateinit var ingredientAdapter: IngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: DetailFragmentArgs by navArgs()
        args.let {
            this.recipe = it.recipeSelected
        }



        binding.apply {
            //todo image
            tvRecipe.text = recipe.title
            tvDirectionToCook.text = recipe.directionToCook
            homeViewModel.getCategoryTitleById(recipe.categoryId).observe(viewLifecycleOwner) { categoryTitle ->
                tvCategory.text = categoryTitle
            }

        }

        initRecycler(recipe.ingredients)

    }

    private fun initRecycler(ingredients: List<Ingredient>) {
        ingredientAdapter.updateData(ingredients)
        binding.rvIngredients.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ingredientAdapter
        }
    }
}