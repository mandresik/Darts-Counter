package cz.mandr.dartscounter.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    val processToCreateGame = MutableLiveData<Boolean>()
    val processToSavedGames = MutableLiveData<Boolean>()

    fun gotoCreateGame(){
        processToCreateGame.value = true
    }

    fun gotoSavedGames(){
        processToSavedGames.value = true
    }

}