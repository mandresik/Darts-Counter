package cz.mandr.dartscounter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CreateGameViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CreateGameViewModel::class.java)){
            return CreateGameViewModel() as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }
}