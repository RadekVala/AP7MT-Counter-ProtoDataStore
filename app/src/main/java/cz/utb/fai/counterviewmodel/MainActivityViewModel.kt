package cz.utb.fai.counterviewmodel

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var email: String = ""
    var name: String = ""
    var counter: Int = 0

    fun increment() {
        counter += 1
    }
}