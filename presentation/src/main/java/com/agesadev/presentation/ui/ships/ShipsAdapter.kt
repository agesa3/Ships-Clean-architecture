package com.agesadev.presentation.ui.ships

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.agesadev.domain.model.Ships
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.SingleShipItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ShipsAdapter : ListAdapter<Ships, ShipsAdapter.ShipsViewHolder>(shipDiffCallBack) {


    inner class ShipsViewHolder(private val binding: SingleShipItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ships: Ships) {
            binding.apply {
                shipName.text = ships.ship_name
                shipStatusText.text = ships.status
                Glide.with(itemView)
                    .load(ships.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ship_holder)
                    .into(shipImage)

            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShipsAdapter.ShipsViewHolder {
        return ShipsViewHolder(
            SingleShipItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShipsAdapter.ShipsViewHolder, position: Int) {
        val ship = getItem(position)
        holder.bind(ship)
    }

}

val shipDiffCallBack = object : DiffUtil.ItemCallback<Ships>() {
    override fun areItemsTheSame(oldItem: Ships, newItem: Ships): Boolean {
        return oldItem.ship_id == newItem.ship_id
    }

    override fun areContentsTheSame(oldItem: Ships, newItem: Ships): Boolean {
        return oldItem == newItem
    }

}