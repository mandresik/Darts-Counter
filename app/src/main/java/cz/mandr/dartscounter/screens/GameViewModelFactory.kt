package cz.mandr.dartscounter.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.Repository

class GameViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }

}