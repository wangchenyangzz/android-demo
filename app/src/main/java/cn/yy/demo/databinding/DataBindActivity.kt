package cn.yy.demo.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import cn.yy.demo.R

class DataBindActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityDataBindBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_bind)
        binding.user = User("wcy", "1234")
    }
}