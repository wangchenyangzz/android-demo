package cn.yy.demo.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import cn.yy.demo.R
import cn.yy.demo.paging3.viewmodel.PagingViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DataBindActivity : AppCompatActivity() {
    private val viewModel by viewModels<DataViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityDataBindBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getStr()
        viewModel.loop()
    }

    fun show(color: Color) {
        when(color) {
            is Color.Red -> TODO()
            is Color.Green -> TODO()
            is Color.Blue -> TODO()
        }
    }
}

sealed class Color {
    class Red(val value: Int): Color()
    class Green(val value: String): Color()
    class Blue(val value: Long): Color()
}