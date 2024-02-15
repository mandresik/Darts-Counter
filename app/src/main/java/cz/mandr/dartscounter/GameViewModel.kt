package cz.mandr.dartscounter

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel() : ViewModel() {

    var count: Int = 0
    var initialScore: Int = 0
    var players = Array<String>(6){""}

    // score of each player in the game
    var playersScore = Array<MutableLiveData<String>>(6){MutableLiveData<String>()}

    // 3 throws of player
    var throws = Array<MutableLiveData<String>>(3){MutableLiveData<String>()}

    // multipliers of each throw
    var throwMultipliers = Array<MutableLiveData<Boolean>>(9){MutableLiveData<Boolean>()}

    // background colors of each player
    var bckColors = Array<MutableLiveData<Int>>(6) { MutableLiveData<Int>() }

    init {
        for(i in 0..2) throwMultipliers[3 * i].value = true
        setColor(0)
    }

    private var currentPlayer : Int = 0

    // evaluates score of throws in one round
    fun handleScore(){
        var multipliers = Array<Int>(3){ 0 }
        var score = Array<Int>(3){ 0 }
        var currentScore: Int = getScore(currentPlayer)
        var scoreBeforeThrows = currentScore

        for(i in 0 .. 2){
            multipliers[i] = getMultiplier(i)
            score[i] = throws[i].value?.toIntOrNull() ?: 0
         //   if(score[i] == null) score[i] = 0
            score[i] *= multipliers[i]
            currentScore -= score[i]
            checkWin(currentScore)
        }

        if(currentScore < 0) { currentScore = scoreBeforeThrows }

        setScore(currentPlayer, currentScore)
        setDefaultRecord()

        currentPlayer = (currentPlayer + 1) % count
        setColor(currentPlayer)
    }

    // returns multiplier of throw with index throwIndex
    private fun getMultiplier(throwIndex : Int) : Int{
        if(throwMultipliers[3 * throwIndex].value == true) return 1
        if(throwMultipliers[3 * throwIndex + 1].value == true) return 2
        if(throwMultipliers[3 * throwIndex + 2].value == true) return 3
        return 0
    }

    // returns score of player with index playerIndex
    private fun getScore(playerIndex : Int) : Int {
        return playersScore[playerIndex].value!!.toInt()
    }

    // sets score of player with index playerIndex
    private fun setScore(playerIndex: Int, score: Int){
        playersScore[playerIndex].value = score.toString()
    }

    // sets record of throws to default values
    private fun setDefaultRecord(){
        for(i in 0..2){
            throws[i].value = ""
            throwMultipliers[3 * i].value = true
        }
    }

    private fun checkWin(score : Int){
        if(score == 0){
            // win screen
            Log.d("Game result", "Game win")
        }
    }

    // sets background color of player with index playerIndex to custom background color
    private fun setColor(playerIndex: Int){
        for(x in bckColors) { x.value = android.R.color.background_light}
        bckColors[playerIndex].value = R.color.backgroundColor
    }

}