package cz.utb.fai.counterviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModelProvider
import cz.utb.fai.counterviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val app = application as MyApplication
        val dataStore: DataStore<CounterData> = app.counterDataStore

        viewModel = ViewModelProvider(
            this,
            MainActivityViewModelFactory(dataStore)
        ).get(MainActivityViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }
}