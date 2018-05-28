package com.yinjin.expandtextview.pagingdemo.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * desc:
 * time: 2018/5/28
 * @author yinYin
 */
@Parcelize
data class Data(val name: String, val age: Int) : Parcelable