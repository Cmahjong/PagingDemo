package com.yinjin.expandtextview.pagingdemo.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.yinjin.expandtextview.pagingdemo.bean.Vehicle

/**
 * desc:
 * time: 2018/5/29
 * @author yinYin
 */
@Dao
interface VehicleDao {
    @Insert
    fun insertVehicle(vehicles: Vehicle)

    @Delete
    fun delete(vehicle: Vehicle)

    @Query("SELECT * FROM VEHICLE WHERE id=:id")
    fun findVehicleById(id: Long): Vehicle

    @Query("SELECT * FROM VEHICLE WHERE user_id=:userId")
    fun findVehicleByUserId(userId: Long): List<Vehicle>

    @Query("SELECT * FROM VEHICLE")
    fun findAllVehicle(): List<Vehicle>
}