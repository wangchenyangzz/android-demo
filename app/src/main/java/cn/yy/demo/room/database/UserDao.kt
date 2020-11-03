package cn.yy.demo.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 *    author : cy.wang
 *    date   : 2020/11/3
 *    desc   :
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM Users WHERE userId= :id")
    suspend fun getUserById(id: String): User

    /**
     * Insert a user in the database. If the user already exists, replace it.
     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Delete all users.
     */
    @Query("DELETE FROM Users")
    fun deleteAllUsers()
}