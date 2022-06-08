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
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {
    private val shoeStoreViewModel: ShoeStoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentShoeDetailBinding>(
            inflater, R.layout.fragment_shoe_detail, container, false
        )

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.saveButton.setOnClickListener {
            val shoeName = binding.ShoeNameEditText.text.toString()
            val shoeCompany = binding.ShoeCompanyEditText.text.toString()
            val shoeSize = binding.ShoeSizeEditText.text.toString()
            val shoeDescription = binding.ShoeDescriptionEditText.text.toString()

            if (shoeName.isBlank() ||
                shoeCompany.isBlank() ||
                shoeSize.toDoubleOrNull() == null ||
                shoeDescription.isBlank()
            ) {
                Toast.makeText(context, getString(R.string.save_error), Toast.LENGTH_SHORT).show()
            } else {
                shoeStoreViewModel.addShoe(
                    Shoe(
                        name = shoeName,
                        size = shoeSize.toDouble(),
                        company = shoeCompany,
                        description = shoeDescription
                    )
                )

                Toast.makeText(context, getString(R.string.save_succeed), Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }

        return binding.root
    }
}