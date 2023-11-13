package cz.utb.fai.counterviewmodel

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import cz.utb.fai.counterviewmodel.data.CounterDataSerializer

class MyApplication : Application() {


    val counterDataStore: DataStore<CounterData> by dataStore(
        fileName = "counter_data.proto",
        serializer = CounterDataSerializer
    )

}