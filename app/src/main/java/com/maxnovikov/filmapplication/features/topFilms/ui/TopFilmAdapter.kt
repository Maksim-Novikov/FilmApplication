package com.maxnovikov.filmapplication.features.topFilms.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maxnovikov.filmapplication.databinding.TopFilmItemBinding
import com.maxnovikov.filmapplication.entity.Film

class TopFilmAdapter(private val onFilmClick: (Film) -> Unit) :
    ListAdapter<Film, ViewHolder>(
        object : DiffUtil.ItemCallback<Film>() {

            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean =
                oldItem == newItem

        }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("TopFilmAdapter", "onCreateViewHolder")
        return ViewHolder(
            TopFilmItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("TopFilmAdapter", "onBindViewHolder $position")

        val item = getItem(position)
        with(holder.binding) {
            topFilmItemName.text = item.name
            topFilmItemYear.text = item.year.toString()
            root.setOnClickListener { onFilmClick(item) }
        }
    }
}


class ViewHolder(val binding: TopFilmItemBinding) : RecyclerView.ViewHolder(binding.root)