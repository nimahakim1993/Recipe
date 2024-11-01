package com.example.recipe2.presentation.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe2.data.model.Ingredient
import com.example.recipe2.databinding.ItemChipsBinding


class ChipsAdapter: RecyclerView.Adapter<ChipsAdapter.MyViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Ingredient>(){
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChipsAdapter.MyViewHolder {
        val binding = ItemChipsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChipsAdapter.MyViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onRemoveClickListener : ((Ingredient) -> Unit)?= null
    fun setOnRemoveClickListener(listener: (Ingredient) -> Unit){
        onRemoveClickListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Ingredient?>?) {
        differ.submitList(newList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: ItemChipsBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(item: Ingredient){
            binding.apply {
                tvTitle.text = "${item.key} : ${item.value}"

                imgRemove.setOnClickListener {
                    onRemoveClickListener?.let {
                        it(item)
                    }
                }
            }

        }
    }
}