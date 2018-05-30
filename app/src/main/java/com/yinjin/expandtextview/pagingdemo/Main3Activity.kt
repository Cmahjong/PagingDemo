package com.yinjin.expandtextview.pagingdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yinjin.expandtextview.pagingdemo.bean.User
import com.yinjin.expandtextview.pagingdemo.bean.Vehicle
import com.yinjin.expandtextview.pagingdemo.dao.UserDao
import com.yinjin.expandtextview.pagingdemo.dao.VehicleDao
import com.yinjin.expandtextview.pagingdemo.daoimpl.DemoRoomDataBase
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.sdk25.coroutines.onClick

class Main3Activity : AppCompatActivity() {
    val userDao: UserDao by lazy {
        DemoRoomDataBase.getInstance(this).getUserDao()
    }
    val vehicle: VehicleDao by lazy {
        DemoRoomDataBase.getInstance(this).getVehicleDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        async {
            userDao.insertUsers((User(1, "姓名1")))
            userDao.insertUsers((User(2, "姓名2")))
            userDao.insertUsers((User(3, "姓名3")))
            vehicle.insertVehicle(Vehicle(1, 1, "车辆1"))
            vehicle.insertVehicle(Vehicle(2, 1, "车辆2"))
            vehicle.insertVehicle(Vehicle(3, 1, "车辆3"))
        }

        button2.onClick {
            launch(UI) {
                val async = async { userDao.findUser(1) }
                textView.text=async.await().name
            }
        }
        button3.onClick {
            launch(UI) {
                val async = async { vehicle.findVehicleById(1) }
                textView.text=async.await().vehicleName
            }
        }
        button4.onClick {
            launch(UI) {
                val async = async { vehicle.findVehicleByUserId(1) }
                textView.text=async.await()[1].vehicleName
            }
        }
        button5.onClick {
            launch(UI) {
                val async = async { vehicle.findAllVehicle() }
                textView.text=async.await()[2].vehicleName
            }
        }
    }
}
