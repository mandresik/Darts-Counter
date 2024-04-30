package cz.mandr.dartscounter.screens

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.mandr.dartscounter.R
import cz.mandr.dartscounter.Repository
import cz.mandr.dartscounter.database.Game
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class GameViewModel(private val repository: Repository) : ViewModel() {

    var count: Int = 0
    var players = Array<String>(6){""}

    // score of each player in the game
    var playersScore = Array<MutableLiveData<String>>(6){MutableLiveData<String>()}

    // 3 throws of player
    var throws = Array<MutableLiveData<String>>(3){MutableLiveData<String>()}

    // multipliers of each throw
    var throwMultipliers = Array<MutableLiveData<Boolean>>(9){MutableLiveData<Boolean>()}

    // background colors of each player
    var bckColors = Array<MutableLiveData<Int>>(6) { MutableLiveData<Int>() }

    var processToMain = MutableLiveData<Boolean>()

    init {
        setDefaultRecord()
    }

     var currentPlayer : Int = 0

    // evaluates score of throws in one round
    fun handleScore(){
        val multipliers = Array<Int>(3){ 0 }
        val score = Array<Int>(3){ 0 }
        var currentScore: Int = getScore(currentPlayer)
        val scoreBeforeThrows = currentScore

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
        for(i in 0 .. 2){
            if(throwMultipliers[3 * throwIndex + i].value == true) return i + 1
        }
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
     fun setColor(playerIndex: Int){
        for(x in bckColors) { x.value = android.R.color.background_light}
        bckColors[playerIndex].value = R.color.backgroundColor
     }

    // saves game to database
    fun saveGame(){
        viewModelScope.launch {
            // setting unique gameID for the game
            var newId: Int = 1
            val cnt: Int = repository.getRowsCount().firstOrNull()!!
            if(cnt != 0){
                val mxId: Int = repository.getMaxId().firstOrNull()!!
                newId = mxId + 1
            }

            val name = "Game $newId"
            val game = Game(newId, name, count, currentPlayer, players.toList(),
                playersScore.map { it.value ?: "" }.toTypedArray().toList() )

            repository.insertGame(game)
        }
        processToMain.value = true
    }

}