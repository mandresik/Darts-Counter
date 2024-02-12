package cz.mandr.dartscounter

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Closeable

class CreateGameViewModel() : ViewModel() {

    var editNick = MutableLiveData<String>()
    var nick1 = MutableLiveData<String>()
    var nick2 = MutableLiveData<String>()
    var nick3 = MutableLiveData<String>()
    var nick4 = MutableLiveData<String>()
    var nick5 = MutableLiveData<String>()
    var nick6 = MutableLiveData<String>()
    var players = ArrayList<String>()
    private var count:Int

    init {
        count = 0
    }


    fun addPlayer(){
        val playerName = editNick.value

        if(count < 6 && playerName.toString().isNotEmpty()){
            count++
            players.add(playerName!!)

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

}