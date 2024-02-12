package cz.mandr.dartscounter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel() : ViewModel() {

    val processToCreateGame = MutableLiveData<Boolean>()

    fun gotoCreateGame(){
        processToCreateGame.value = true
    }

}