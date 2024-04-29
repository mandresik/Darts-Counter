package cz.mandr.dartscounter.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    val processToCreateGame = MutableLiveData<Boolean>()

    fun gotoCreateGame(){
        processToCreateGame.value = true
    }

}