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

   //     viewModel.players = intent.getStringArrayExtra("PLAYERS")

        var cnt = intent.getIntExtra("COUNT", 0)
        viewModel.count.value = cnt


        var arrList = intent.getStringArrayListExtra("PLAYERS")
        if (arrList != null) {
            viewModel.players = arrList
        }
        /*
        val nick1 = intent.getStringArrayExtra("PLAYERS")!![0]
        binding.textViewGamePlayer1.text = nick1.toString() */
    }
}