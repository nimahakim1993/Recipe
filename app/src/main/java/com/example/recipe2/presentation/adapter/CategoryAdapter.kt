package com.example.recipe2.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe2.R
import com.example.recipe2.data.entity.Category
import com.example.recipe2.databinding.ItemCategoryBinding


class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.MyViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.MyViewHolder, position: Int) {
        val ticket = differ.currentList[position]
        holder.bind(ticket)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Category) -> Unit)?= null
    fun setOnItemClickListener(listener: (Category) -> Unit){
        onItemClickListener = listener
    }

    fun updateData(newList: List<Category?>) {
        differ.submitList(newList)
    }

    inner class MyViewHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Category) {
            binding.apply {
                tvTitle.text = item.title
                //show default when is 0 because user cant add image
                ivCategory.setImageResource(if (item.imageUrl == 0) R.drawable.img_default else item.imageUrl)

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }
}