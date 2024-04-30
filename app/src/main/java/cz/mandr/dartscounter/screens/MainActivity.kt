package cz.mandr.dartscounter.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory())
            .get(MainViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.processToCreateGame.observe(this, { value ->
            if(value){
                val intent = Intent(this, CreateGameActivity::class.java)
                startActivity(intent)
                viewModel.processToCreateGame.value = false
            }
        })


        viewModel.processToSavedGames.observe(this, { value ->
            if(value){
                val intent = Intent(this, SavedGamesActivity::class.java)
                startActivity(intent)
                viewModel.processToSavedGames.value = false
            }
        })

    }
}