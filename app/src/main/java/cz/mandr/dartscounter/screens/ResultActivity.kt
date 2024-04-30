package cz.mandr.dartscounter.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cz.mandr.dartscounter.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ResultViewModelFactory())
            .get(ResultViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.winnerName = intent.getStringExtra("WINNER").toString()

        viewModel.processToMain.observe(this, { value ->
            if(value){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                viewModel.processToMain.value = false
            }
        })

    }

}