package com.yinjin.expandtextview.pagingdemo.daoimpl

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.yinjin.expandtextview.pagingdemo.bean.User
import com.yinjin.expandtextview.pagingdemo.bean.Vehicle
import com.yinjin.expandtextview.pagingdemo.dao.UserDao
import com.yinjin.expandtextview.pagingdemo.dao.VehicleDao

/**
 * desc:
 * time: 2018/5/29
 * @author yinYin
 */
@Database(entities = [User::class, Vehicle::class], version = 1, exportSchema = false)
abstract class DemoRoomDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getVehicleDao(): VehicleDao

    companion object {
        var INSTANCE: DemoRoomDataBase? = null
        fun getInstance(context: Context): DemoRoomDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, DemoRoomDataBase::class.java, "demo_20180531").build()
            }
            return INSTANCE!!
        }
    }
}