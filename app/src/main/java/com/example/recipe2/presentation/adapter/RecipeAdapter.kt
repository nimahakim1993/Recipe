package com.example.recipe2.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe2.data.entity.Category
import com.example.recipe2.data.entity.Recipe
import com.example.recipe2.databinding.ItemCategoryBinding
import com.example.recipe2.databinding.ItemRecipeBinding


class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.MyViewHolder>() {
    private lateinit var categoryTitle :String

    private val callback = object : DiffUtil.ItemCallback<Recipe>(){
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeAdapter.MyViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeAdapter.MyViewHolder, position: Int) {
        val ticket = differ.currentList[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Recipe) -> Unit)?= null
    fun setOnItemClickListener(listener: (Recipe) -> Unit){
        onItemClickListener = listener
    }

    fun updateData(newList: List<Recipe?>, categoryTitle: String) {
        this.categoryTitle = categoryTitle
        differ.submitList(newList)
    }

    inner class MyViewHolder(private val binding: ItemRecipeBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Recipe) {
            binding.apply {
                tvCategory.text = categoryTitle
                tvRecipe.text = item.title
                ivRecipe.setImageResource(item.imageUrl)

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }
}