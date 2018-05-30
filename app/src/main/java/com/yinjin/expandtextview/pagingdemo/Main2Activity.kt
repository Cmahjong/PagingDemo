package com.yinjin.expandtextview.pagingdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yinjin.expandtextview.pagingdemo.bean.User
import com.yinjin.expandtextview.pagingdemo.bean.Vehicle
import com.yinjin.expandtextview.pagingdemo.dao.UserDao
import com.yinjin.expandtextview.pagingdemo.dao.VehicleDao
import com.yinjin.expandtextview.pagingdemo.daoimpl.DemoRoomDataBase
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.experimental.async

class Main2Activity : AppCompatActivity() {
    val userDao: UserDao by lazy {
        DemoRoomDataBase.getInstance(this).getUserDao()
    }
    val vehicle: VehicleDao by lazy {
        DemoRoomDataBase.getInstance(this).getVehicleDao()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        button.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        button5.setOnClickListener {
            startActivity(Intent(this,Main3Activity::class.java))
        }

    }
}
