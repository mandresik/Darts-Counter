package cz.mandr.dartscounter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel() : ViewModel() {

    var count: Int = 0
    var initialScore: Int = 0
    var players = Array<String>(6){""}
  //  var arrScore = Array<Int>(count) { initialScore }

    var scoreMutable1 = MutableLiveData<String>()
    var scoreMutable2 = MutableLiveData<String>()
    var scoreMutable3 = MutableLiveData<String>()
    var scoreMutable4 = MutableLiveData<String>()
    var scoreMutable5 = MutableLiveData<String>()
    var scoreMutable6 = MutableLiveData<String>()



}