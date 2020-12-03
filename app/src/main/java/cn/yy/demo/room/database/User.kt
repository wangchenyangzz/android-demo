package cn.yy.demo.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 *    author : cy.wang
 *    date   : 2020/11/3
 *    desc   :
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userId", defaultValue = "123")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "age")
    val age: Int
)