package cz.mandr.dartscounter.screens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateGameViewModel() : ViewModel() {

    var checked101 = MutableLiveData<Boolean>()
    var checked201 = MutableLiveData<Boolean>()
    var checked301 = MutableLiveData<Boolean>()
    var checked501 = MutableLiveData<Boolean>()
    var checked701 = MutableLiveData<Boolean>()

    var editNick = MutableLiveData<String>()
    var nick1 = MutableLiveData<String>()
    var nick2 = MutableLiveData<String>()
    var nick3 = MutableLiveData<String>()
    var nick4 = MutableLiveData<String>()
    var nick5 = MutableLiveData<String>()
    var nick6 = MutableLiveData<String>()
    var players = Array<String>(6){""}
    var count: Int = 0
    var initialScore = Array<String>(6){""}

    val processToGame = MutableLiveData<Boolean>()

    init {
        checked301.value = true
    }

    // adds player to list of players
    fun addPlayer(){
        val playerName = editNick.value

        if(count < 6 && playerName.toString().trim().isNotEmpty()){
            players[count] = playerName!!
            count++

            when(count){
                1 -> nick1.value = playerName!!
                2 -> nick2.value = playerName!!
                3 -> nick3.value = playerName!!
                4 -> nick4.value = playerName!!
                5 -> nick5.value = playerName!!
                6 -> nick6.value = playerName!!
            }
        }

        editNick.value = ""
    }

    // sets initial score for all players and sets processToGame true
    fun gotoGame(){
        val score = getScore0()
        for(i in 0 ..< count) initialScore[i] = score.toString()
        processToGame.value = true
    }

    // returns initial score
    private fun getScore0() : Int{
        if(checked101.value == true) return 101
        if(checked201.value == true) return 201
        if(checked301.value == true) return 301
        if(checked501.value == true) return 501
        if(checked701.value == true) return 701
        return -1
    }

}