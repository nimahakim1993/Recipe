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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipe2.data.entity.Category
import com.example.recipe2.databinding.FragmentAddCategoryBinding
import com.example.recipe2.databinding.FragmentHomeBinding
import com.example.recipe2.presentation.adapter.CategoryAdapter
import com.example.recipe2.presentation.viewmodel.HomeViewModel
import com.example.recipe2.presentation.viewmodel.HomeViewModelFactory
import com.example.recipe2.presentation.viewmodel.MainViewModel
import com.example.recipe2.presentation.viewmodel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddCategoryFragment: Fragment() {
    private lateinit var binding: FragmentAddCategoryBinding

    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
    private val homeViewModel: HomeViewModel by viewModels{ homeViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cvAddImage.setOnClickListener {
                Toast.makeText(requireContext(), "Not Implemented", Toast.LENGTH_SHORT).show()
            }
            btnAdd.setOnClickListener {
                if (validateInfo()){
                    val category = Category(
                        title = etCategory.text.toString(),
                        imageUrl = 0
                    )
                    homeViewModel.addCategory(category)

                    findNavController().popBackStack()
                }
                else
                    Toast.makeText(requireContext(), "ورود عنوان الزامی است", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateInfo(): Boolean {  //very sample validation
        val title = binding.etCategory.text.toString()
        return title.isNotEmpty()
    }

}