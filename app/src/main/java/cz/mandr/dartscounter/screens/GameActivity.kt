package cz.mandr.dartscounter.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.MyApplication
import cz.mandr.dartscounter.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as MyApplication
        viewModel = ViewModelProvider(this, GameViewModelFactory(app.repository))
            .get(GameViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // number of players
        viewModel.count = intent.getIntExtra("COUNT", 0)

        // index of player to play
        val firstToPlay = intent.getIntExtra("PLAYER0", 0)
        viewModel.setColor(firstToPlay)
        viewModel.currentPlayer = firstToPlay

        // nicknames
        val arrList = intent.getStringArrayExtra("PLAYERS")
        if (arrList != null) { viewModel.players = arrList }

        // score of all players
        val arrScore = intent.getStringArrayExtra("SCORE0")
        for(i in 0..< viewModel.count){
            viewModel.playersScore[i].value = arrScore!![i]
        }


        viewModel.processToMain.observe(this, { value ->
            if(value){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                viewModel.processToMain.value = false
            }
        })

        viewModel.processToResult.observe(this, { value ->
            if(value){
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("WINNER", viewModel.players[viewModel.currentPlayer])
                startActivity(intent)
                viewModel.processToResult.value = false
            }
        })

    }
}
