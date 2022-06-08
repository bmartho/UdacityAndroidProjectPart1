package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListItemBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()
    lateinit var binding: FragmentShoeListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        shoeStoreViewModel.shoes.observe(viewLifecycleOwner) { shoeList ->
            if (shoeList.isNotEmpty()) {
                addListInLayout(shoeList)
            }
        }

        return binding.root
    }

    private fun addListInLayout(shoeList: List<Shoe>) {
        shoeList.forEach { shoe ->
            val itemBinding = ShoeListItemBinding.inflate(LayoutInflater.from(context))
            itemBinding.shoe = shoe

            binding.shoeListContainer.addView(itemBinding.root)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoelist_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> navigateToLoginScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLoginScreen() {
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }
}