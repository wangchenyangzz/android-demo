package cn.yy.demo.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import cn.yy.demo.R
import cn.yy.demo.room.database.User
import cn.yy.demo.room.database.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val dao = UserDatabase.getInstance(application)
            .userDao()

        lifecycleScope.launch {
            val user = withContext(Dispatchers.Default) {
                dao.insertUser(User(id = "1", name = "wcy1", age = 21))
                val user = dao.getUserById("1")
                user
            }
            Toast.makeText(this@RoomActivity, "user age ${user.age}", Toast.LENGTH_SHORT).show()
        }
    }
}