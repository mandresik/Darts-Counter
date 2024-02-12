package cz.mandr.dartscounter

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
    }
}