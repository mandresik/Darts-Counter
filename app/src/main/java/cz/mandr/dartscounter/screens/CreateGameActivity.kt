package cz.mandr.dartscounter.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.databinding.ActivityCreateGameBinding

class CreateGameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGameBinding
    private lateinit var viewModel: CreateGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, CreateGameViewModelFactory())
            .get(CreateGameViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.processToGame.observe(this, { value ->
            if(value){
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra("COUNT", viewModel.count)
                intent.putExtra("PLAYER0", 0)
                intent.putExtra("SCORE0", viewModel.initialScore)
                intent.putExtra("PLAYERS", viewModel.players)
                startActivity(intent)
                viewModel.processToGame.value = false
            }
        })

    }
}