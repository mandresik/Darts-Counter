package cz.mandr.dartscounter.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.Repository

class SavedGamesViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SavedGamesViewModel::class.java)){
            return SavedGamesViewModel(repository) as T
        }
        throw IllegalArgumentException("Wrong ViewModel class")
    }

}