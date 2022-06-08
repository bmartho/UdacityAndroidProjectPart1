package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.shoesStoreViewModel = shoeStoreViewModel
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        shoeStoreViewModel.saveSucceed.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                Toast.makeText(context, getString(R.string.save_succeed), Toast.LENGTH_SHORT).show()
                shoeStoreViewModel.clearStatus()
                findNavController().navigateUp()
            }
        }

        shoeStoreViewModel.saveError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                Toast.makeText(context, getString(R.string.save_error), Toast.LENGTH_SHORT).show()
                shoeStoreViewModel.clearStatus()
            }
        }

        return binding.root
    }
}