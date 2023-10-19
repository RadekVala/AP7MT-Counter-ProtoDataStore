package cz.utb.fai.counterviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.counterviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // update the UI immediately
        updateCounterUI()

        // btnSave click event handler
        binding.btnSave.setOnClickListener {
            // get the user input from editTextName
            viewModel.name = binding.editTextName.text.toString()
            viewModel.email = binding.editTextEmail.text.toString()
            // set the input into textName text field
            binding.textName.text = viewModel.name
        }

        // btnIncrement click event handler, will increment the counter and show it
        binding.btnIncrement.setOnClickListener {
            viewModel.increment()
            updateCounterUI()
        }
    }

    fun updateCounterUI(){
        binding.textName.text = viewModel.name
        binding.textCounter.text = viewModel.counter.toString()
    }
}