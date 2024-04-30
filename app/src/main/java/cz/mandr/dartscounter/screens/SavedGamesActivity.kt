package cz.mandr.dartscounter.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.MyApplication
import cz.mandr.dartscounter.databinding.ActivitySavedGamesBinding

class SavedGamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedGamesBinding
    private lateinit var viewModel: SavedGamesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySavedGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as MyApplication
        viewModel = ViewModelProvider(this, SavedGamesViewModelFactory(app.repository))
            .get(SavedGamesViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }

}