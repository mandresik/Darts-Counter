package cz.mandr.dartscounter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel() : ViewModel() {


    var count = MutableLiveData<Int>()

    var nick1 = MutableLiveData<String>()

    var players = ArrayList<String>()
    var arrScore = IntArray(players.size) { 301 }




}