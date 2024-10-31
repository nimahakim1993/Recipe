package com.example.recipe2.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe2.data.model.Ingredient
import com.example.recipe2.databinding.ItemIngredientBinding
import com.example.recipe2.databinding.ItemRecipeBinding


class IngredientAdapter: RecyclerView.Adapter<IngredientAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Ingredient>(){
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientAdapter.MyViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientAdapter.MyViewHolder, position: Int) {
        val ticket = differ.currentList[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

//    private var onItemClickListener : ((Int) -> Unit)?= null
//    fun setOnItemClickListener(listener: (Int) -> Unit){
//        onItemClickListener = listener
//    }

    fun updateData(newList: List<Ingredient?>) {
        differ.submitList(newList)
    }

    inner class MyViewHolder(private val binding: ItemIngredientBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Ingredient) {
            binding.apply {
                tvKey.text = item.key
                tvValue.text = item.value

            }
        }
    }
}