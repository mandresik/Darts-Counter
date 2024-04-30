package cz.mandr.dartscounter.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel() : ViewModel() {

    var winnerName: String = ""

    var processToMain = MutableLiveData<Boolean>()

    fun gotoMain(){
        processToMain.value = true
    }

}