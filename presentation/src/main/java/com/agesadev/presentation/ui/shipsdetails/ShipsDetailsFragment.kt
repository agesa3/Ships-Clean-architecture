package com.agesadev.presentation.ui.shipsdetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.agesadev.domain.model.Ships
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.FragmentShipsDertailsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ShipsDetailsFragment : Fragment() {

    private val args by navArgs<ShipsDetailsFragmentArgs>()
    private var _binding: FragmentShipsDertailsBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShipsDertailsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        displayShipDetails()
    }


    private fun displayShipDetails() {
        binding?.apply {
            bottomTitle.text = args.shipModel?.ship_name ?: "N/A"
            Glide.with(this@ShipsDetailsFragment)
                .load(args.shipModel?.image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.ship_holder)
                .into(shipDescImage)
            shipDetailsType.text = args.shipModel?.ship_type ?: "N/A"
            shipDetailsID.text = args.shipModel?.ship_id ?: "N/A"
            shipDetailsModel.text = args.shipModel?.ship_type ?: "N/A"
            shipDetailsWeight.text = args.shipModel?.weight_kg.toString() ?: "N/A"
            shipDetailsSpeed.text = args.shipModel?.speed_kn.toString() ?: "N/A"
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

}