package cn.yy.demo.databinding

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*

/**
 *    author : cy.wang
 *    date   : 2020/8/12
 *    desc   :
 */
class DataViewModel : ViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
            get() = _name

    val id: LiveData<String> = Transformations.map(_name) {
        "$it + 123"
    }

    fun getStr() {
        viewModelScope.launch {
            _name.value = "wcy"
            delay(1000)
            _name.value = "cxy"
        }
    }

    fun loop() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }) {
            withContext(Dispatchers.IO) {
                var i = 0
                while (i < 1000000) {
                    val user = User("", "")
                    i++
                }
            }
            Log.d("wcy", "zzz")
        }
    }
}