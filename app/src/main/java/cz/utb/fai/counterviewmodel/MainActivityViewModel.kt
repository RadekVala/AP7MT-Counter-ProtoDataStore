package cz.utb.fai.counterviewmodel


import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


data class CounterDataFlow(val counter: Int, val email: String, val name: String)

class MainActivityViewModel(dataStore: DataStore<CounterData>) : ViewModel() {

    val dataStore = dataStore

    // MutableLiveData for private mutable counter
    private val _counter = MutableLiveData<Int>()

    // Expose LiveData to observe changes
    val counter: LiveData<Int> get() = _counter

    // MutableLiveData for easy two-way data binding
    val nameMutable = MutableLiveData<String>()
    val emailMutable = MutableLiveData<String>()

    // Initial values
    init {

        val counterDataFlow: Flow<CounterDataFlow> = dataStore.data
            .map { data ->
                CounterDataFlow(data.counter, data.email, data.name)
            }

        viewModelScope.launch {
            counterDataFlow.collect { data ->
                _counter.value = data.counter
                emailMutable.value = data.email
                nameMutable.value = data.name
            }
        }
    }

    fun increment() {
        _counter.value = ( _counter.value ?: 0) + 1

        viewModelScope.launch {
            updateCounterStore()
        }
    }

    fun btnSave() {

        viewModelScope.launch {
            updateStoreEmailName()
        }
    }

    suspend fun updateStoreEmailName() {

        dataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setEmail(emailMutable.value)
                .setName(nameMutable.value)
                .build()
        }

    }
    suspend fun updateCounterStore() {

            dataStore.updateData { currentCounter ->
                currentCounter.toBuilder()
                    // increment current state of stored counter
                    .setCounter(currentCounter.counter + 1)
                    .build()
            }

    }

}
