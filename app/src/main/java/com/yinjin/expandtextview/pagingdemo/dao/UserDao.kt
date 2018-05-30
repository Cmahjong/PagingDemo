package com.yinjin.expandtextview.pagingdemo.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.yinjin.expandtextview.pagingdemo.bean.User

/**
 * desc:
 * time: 2018/5/29
 * @author yinYin
 */
@Dao
interface UserDao {
    @Insert
    fun insertUsers(users: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM USER WHERE id=:id")
    fun findUser(id: Long): User

    @Query("SELECT * FROM USER")
    fun findAllUser(): List<User>

}