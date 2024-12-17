package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository):ViewModel() {
    //var wishIdState by mutableStateOf(0L)
    var wishtitlestate by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onwishtitlechange(newValue:String){
        wishtitlestate = newValue
    }
    fun onwishDescriptionchange(newValue:String){
        wishDescriptionState = newValue
    }

    fun addWish(wish: Wish){
        viewModelScope.launch(context = Dispatchers.IO){
            wishRepository.addAWish(wish)
        }
    }

    fun updateWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateAWish(wish)

        }
    }

    fun deleteWish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO){
            wishRepository.deleteAWish(wish)

        }
    }

    fun getWishById(id:Long) : Flow<Wish> {
        return wishRepository.getAWishById(id)
    }

    var getAllWishes : Flow<List<Wish>> = wishRepository.getAllWishes()
/*
    lateinit var getAllWishes : Flow<List<Wish>>

    init {
        getAllWishes=wishRepository.getAllWishes()
    }*/
}