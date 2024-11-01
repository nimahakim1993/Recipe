package com.example.recipe2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipe2.R
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.data.model.Ingredient
import com.example.recipe2.databinding.FragmentAddRecipeBinding
import com.example.recipe2.presentation.adapter.ChipsAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.presentation.viewmodel.MainViewModel
import com.example.recipe2.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddRecipeFragment: Fragment() {
    private lateinit var binding: FragmentAddRecipeBinding
    private var ingredientList: ArrayList<Ingredient> = arrayListOf()
    private lateinit var categorySelected: Category

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{ homeViewModelFactory }

    @Inject
    lateinit var chipsAdapter: ChipsAdapter

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by activityViewModels{mainViewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AddRecipeFragmentArgs by navArgs()
        args.let {
            this.categorySelected = it.categorySelected
        }

        initChipsRecycler(ingredientList)

        binding.apply {
            cvAddImage.setOnClickListener {
                Toast.makeText(requireContext(), "Not Implemented", Toast.LENGTH_SHORT).show()
            }
            tilCategory.text = getString(R.string.title_category, categorySelected.title)
            ibAddIngredient.setOnClickListener {
                val key = etKeyIngredient.text.toString()
                val value = etValueIngredient.text.toString()
                if (key.isNotEmpty() && value.isNotEmpty()){
                    ingredientList.add(Ingredient(key, value))
                    chipsAdapter.updateData(ingredientList)

                    etKeyIngredient.setText("")
                    etValueIngredient.setText("")
                }
            }

            btnAdd.setOnClickListener {
                if (validateInfo()){
                    val recipe = Recipe(
                        title = etTitle.text.toString(),
                        categoryId = categorySelected.id,
                        imageUrl = 0,
                        ingredients = ingredientList,
                        directionToCook = etDirectionToCook.text.toString()
                    )
                    homeViewModel.addRecipe(recipe)

                    findNavController().popBackStack()
                }
                else
                    Toast.makeText(requireContext(), "ورود عنوان و دستور پخت و حداقل یک مواد اولیه الزامی است", Toast.LENGTH_SHORT).show()
            }
        }

        chipsAdapter.setOnRemoveClickListener {
            ingredientList.remove(it)
            chipsAdapter.updateData(ingredientList)
        }

    }

    private fun initChipsRecycler(selectedList: ArrayList<Ingredient>) {
        chipsAdapter.updateData(selectedList)
        binding.rvItems.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = chipsAdapter
        }
    }

    private fun validateInfo(): Boolean {  //very sample validation
        val title = binding.etTitle.text.toString()
        val directionToCook = binding.etTitle.text.toString()
        if (title.isEmpty())
            return false
        if (directionToCook.isEmpty())
            return false
        if (ingredientList.isEmpty())
            return false

        return true
    }

}