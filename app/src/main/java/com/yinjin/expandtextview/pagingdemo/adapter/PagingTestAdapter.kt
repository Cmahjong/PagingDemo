package com.yinjin.expandtextview.pagingdemo.adapter

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.yinjin.expandtextview.pagingdemo.Main2Activity
import com.yinjin.expandtextview.pagingdemo.MainActivity
import com.yinjin.expandtextview.pagingdemo.R
import com.yinjin.expandtextview.pagingdemo.bean.Data
import kotlinx.android.synthetic.main.item.view.*

/**
 * desc:
 * time: 2018/5/28
 * @author yinYin
 */
class PagingTestAdapter( diffCallback: DiffUtil.ItemCallback<Data>, data: ArrayList<Data>) : PagedListAdapter<Data, MyViewHolder>(diffCallback) {
    private val data = data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder{
        val view = (parent.context as (MainActivity)).layoutInflater.inflate(R.layout.item, null)
        view.layoutParams=ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.view.apply {
            tv_name.text = data[position].name
            tv_age.text = data[position].age.toString()
        }
    }
}

class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

}