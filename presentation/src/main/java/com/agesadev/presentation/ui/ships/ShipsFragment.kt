package com.agesadev.presentation.ui.ships

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.agesadev.domain.model.Ships
import com.agesadev.presentation.R
import com.agesadev.presentation.databinding.FragmentShipsBinding
import com.agesadev.presentation.ui.shipsdetails.ShipsDetailsFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShipsFragment : Fragment(), ItemOnClick {

    private var _binding: FragmentShipsBinding? = null
    private val binding get() = _binding

    private val modalBottomSheet = ShipsDetailsFragment()


    private lateinit var shipsAdapter: ShipsAdapter
    private val shipsViewModel: ShipsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShipsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun searchShip() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                filterShips(searchText)
                return false
            }

        })
    }

    private fun filterShips(text: String) {
        val filteredShips: ArrayList<Ships> = ArrayList()
        for (ship in shipsAdapter.currentList) {
            if (ship.ship_name?.lowercase()?.trim()?.contains(text.lowercase().trim()) == true) {
                filteredShips.add(ship)
            }
        }
        if (filteredShips.isEmpty()) {
        } else {
            shipsAdapter.submitList(filteredShips)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        getAndObserveShips()
        searchShip()

    }

    private fun setUpRecyclerView() {
        shipsAdapter = ShipsAdapter(this)
        binding?.shipsRecylerView?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = shipsAdapter
        }
        binding?.retryButton?.setOnClickListener {
            doRefresh()
        }

    }

    private fun getAndObserveShips() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                shipsViewModel.ships.collectLatest { state ->
                    when {
                        state.data.isNotEmpty() -> {
                            shipsAdapter.submitList(state.data)
                            Log.d("Home", "getAndObserveShips: Ships are ${state.data}")
                            hideProgressBar()


                        }
                        state.isLoading -> {
                            showProgressBar()
                            Log.d("Home", "We are Loading")
                        }
                        state.error != null -> {
                            hideProgressBar()
                            showNetworkErrorImage()
                            binding?.root?.let { showSnackBar(it) }
                        }
                        else -> {

                        }
                    }

                }
            }
        }
    }

    private fun showSnackBar(view: View) {
        Snackbar.make(
            view,
            "Error loading ships",
            Snackbar.LENGTH_LONG
        )
            .setAction("Retry") {
                doRefresh()
            }.show()
    }

    private fun doRefresh() {
        shipsViewModel.refresh()
        hideProgressBar()
        hideNetWorkErrorImage()
        getAndObserveShips()
    }

    private fun hideProgressBar() {
        binding?.progressBar?.visibility = View.GONE
    }

    private fun showNetworkErrorImage() {
        binding?.networkError?.visibility = View.VISIBLE
        binding?.retryButton?.visibility = View.VISIBLE
    }

    private fun hideNetWorkErrorImage() {
        binding?.networkError?.visibility = View.GONE
        binding?.retryButton?.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding?.progressBar?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(ship: Ships) {
        val actions = ShipsFragmentDirections.actionShipsFragmentToShipsDetailsFragment(ship)
        findNavController().navigate(actions)
    }

}

