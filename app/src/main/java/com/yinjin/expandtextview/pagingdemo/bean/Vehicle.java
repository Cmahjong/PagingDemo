package com.yinjin.expandtextview.pagingdemo.bean;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * desc:
 * time: 2018/5/29
 *
 * @author yinYin
 */
@Entity(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "user_id"),tableName = "VEHICLE")
public class Vehicle {
    @PrimaryKey
    private Long id;
    @ColumnInfo(name = "user_id")
    private Long userId;
    @ColumnInfo(name = "vehicle_name")
    private String vehicleName;

    public Vehicle() {
    }

    public Vehicle(Long id, Long userId, String vehicleName) {
        this.id = id;
        this.userId = userId;
        this.vehicleName = vehicleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }
}
