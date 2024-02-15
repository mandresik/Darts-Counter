package cz.mandr.dartscounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var viewModel: GameViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, GameViewModelFactory())
            .get(GameViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.count = intent.getIntExtra("COUNT", 0)
        viewModel.initialScore = intent.getIntExtra("SCORE0", 0)

        var arrList = intent.getStringArrayExtra("PLAYERS")
        if (arrList != null) { viewModel.players = arrList }

        for(i in 0..< viewModel.count){
            viewModel.playersScore[i].value = viewModel.initialScore.toString()
        }

    }
}
