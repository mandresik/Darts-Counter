package cz.mandr.dartscounter.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mandr.dartscounter.Repository
import cz.mandr.dartscounter.database.Game
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SavedGamesViewModel(private val repository: Repository) : ViewModel() {

    var gamesMut = MutableLiveData<List<Game>>()

    fun getGames(){
        viewModelScope.launch {
            gamesMut.value = repository.getAllGames().firstOrNull()
        }
    }

    fun deleteGame(gameId: Int){
        viewModelScope.launch {
            repository.deleteGameById(gameId)
        }
    }

}