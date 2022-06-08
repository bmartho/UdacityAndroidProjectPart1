package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeStoreViewModel : ViewModel() {
    lateinit var newShoe: Shoe

    private val _shoes = MutableLiveData<MutableList<Shoe>>()
    val shoes: LiveData<MutableList<Shoe>>
        get() = _shoes

    private val _saveSucceed = MutableLiveData<Boolean>()
    val saveSucceed: LiveData<Boolean>
        get() = _saveSucceed

    private val _saveError = MutableLiveData<Boolean>()
    val saveError: LiveData<Boolean>
        get() = _saveError

    init {
        _shoes.value = mutableListOf()
    }

    fun addShoe() {
        if (newShoe.name.isBlank() ||
            newShoe.company.isBlank() ||
            newShoe.size.toDoubleOrNull() == null ||
            newShoe.description.isBlank()
        ) {
            _saveError.value = true
        } else {
            newShoe.size = newShoe.size.toDouble().toString()

            _shoes.value?.add(newShoe)
            _saveSucceed.value = true
        }
    }

    fun clearStatus() {
        _saveSucceed.value = false
        _saveError.value = false
    }

    fun initializeNewShoe() {
        newShoe = Shoe("", "", "", "")
    }
}