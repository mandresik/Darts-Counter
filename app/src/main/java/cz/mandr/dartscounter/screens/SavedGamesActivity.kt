package cz.mandr.dartscounter.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.mandr.dartscounter.MyApplication
import cz.mandr.dartscounter.adapter.GameAdapter
import cz.mandr.dartscounter.database.Game
import cz.mandr.dartscounter.databinding.ActivitySavedGamesBinding

class SavedGamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedGamesBinding
    private lateinit var viewModel: SavedGamesViewModel
    private lateinit var adapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySavedGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val app = application as MyApplication
        viewModel = ViewModelProvider(this, SavedGamesViewModelFactory(app.repository))
            .get(SavedGamesViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = GameAdapter(ArrayList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // observes changes in games list
        viewModel.gamesMut.observe(this, Observer { gamesMut ->
            adapter.updateData(gamesMut)
        })

        // get game list from database
        viewModel.getGames()

        // listener for deleting game
        adapter.setDeleteClickListener(object: GameAdapter.GameDeleteClickListener{
            override fun onDeleteClick(game: Game){
                viewModel.deleteGame(game.gameID)
                viewModel.getGames()
            }
        })



    }
}