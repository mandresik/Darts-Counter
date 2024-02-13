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

    var throw1 = MutableLiveData<String>()
    var throw2 = MutableLiveData<String>()
    var throw3 = MutableLiveData<String>()

    var throwMult1x1 = MutableLiveData<Boolean>()
    var throwMult1x2 = MutableLiveData<Boolean>()
    var throwMult1x3 = MutableLiveData<Boolean>()
    var throwMult2x1 = MutableLiveData<Boolean>()
    var throwMult2x2 = MutableLiveData<Boolean>()
    var throwMult2x3 = MutableLiveData<Boolean>()
    var throwMult3x1 = MutableLiveData<Boolean>()
    var throwMult3x2 = MutableLiveData<Boolean>()
    var throwMult3x3 = MutableLiveData<Boolean>()

    var bckColor = Array<MutableLiveData<Int>>(6) { MutableLiveData<Int>() }

    init {
        throwMult1x1.value = true
        throwMult2x1.value = true
        throwMult3x1.value = true
        setColor(0)
    }

    private var currentPlayer : Int = 0

    fun handleScore(){
        var mult1: Int = getMultiplier(1)
        var mult2: Int = getMultiplier(2)
        var mult3: Int = getMultiplier(3)
        var currentScore: Int = getScore(currentPlayer)
        var scoreBeforeThrows = currentScore

        var score1: Int = throw1.value?.toIntOrNull() ?: 0
        var score2: Int = throw2.value?.toIntOrNull() ?: 0
        var score3: Int = throw3.value?.toIntOrNull() ?: 0
        if(score1 == null) { score1 = 0 }
        if(score2 == null) { score2 = 0 }
        if(score3 == null) { score3 = 0 }

        score1 *= mult1
        score2 *= mult2
        score3 *= mult3

        currentScore -= score1
        checkWin(currentScore)
        currentScore -= score2
        checkWin(currentScore)
        currentScore -= score3
        checkWin(currentScore)

        if(currentScore < 0) { currentScore = scoreBeforeThrows }

        setScore(currentPlayer, currentScore)
        setDefaultRecord()

        currentPlayer = (currentPlayer + 1) % count
        setColor(currentPlayer)
    }

    private fun getMultiplier(multiplierIndex : Int) : Int{
        when(multiplierIndex){
            1 -> {
                if(throwMult1x1.value == true) return 1
                if(throwMult1x2.value == true) return 2
                if(throwMult1x3.value == true) return 3
            }
            2 -> {
                if(throwMult2x1.value == true) return 1
                if(throwMult2x2.value == true) return 2
                if(throwMult2x3.value == true) return 3
            }
            3 -> {
                if(throwMult3x1.value == true) return 1
                if(throwMult3x2.value == true) return 2
                if(throwMult3x3.value == true) return 3
            }
        }
        return 0
    }

    private fun getScore(playerIndex : Int) : Int {
        when(playerIndex){
            0 -> return scoreMutable1.value!!.toInt()
            1 -> return scoreMutable2.value!!.toInt()
            2 -> return scoreMutable3.value!!.toInt()
            3 -> return scoreMutable4.value!!.toInt()
            4 -> return scoreMutable5.value!!.toInt()
            5 -> return scoreMutable6.value!!.toInt()
        }
        return 0
    }

    private fun setScore(playerIndex: Int, score: Int){
        when(playerIndex){
            0 -> scoreMutable1.value = score.toString()
            1 -> scoreMutable2.value = score.toString()
            2 -> scoreMutable3.value = score.toString()
            3 -> scoreMutable4.value = score.toString()
            4 -> scoreMutable5.value = score.toString()
            5 -> scoreMutable6.value = score.toString()
        }
    }

    private fun setDefaultRecord(){
        throw1.value = ""
        throw2.value = ""
        throw3.value = ""
        throwMult1x1.value = true
        throwMult2x1.value = true
        throwMult3x1.value = true
    }

    private fun checkWin(score : Int){
        if(score == 0){
            // win screen
            Log.d("Game result", "Game win")
        }
    }

    private fun setColor(playerIndex: Int){
        for(x in bckColor) { x.value = android.R.color.background_light}
        bckColor[playerIndex].value = R.color.backgroundColor
    }

}