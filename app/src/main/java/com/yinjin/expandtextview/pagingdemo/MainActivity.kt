package com.yinjin.expandtextview.pagingdemo

import android.arch.paging.PagedList
import android.arch.paging.PositionalDataSource
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.yinjin.expandtextview.pagingdemo.adapter.PagingTestAdapter
import com.yinjin.expandtextview.pagingdemo.bean.Data
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val data: ArrayList<Data> by lazy { arrayListOf<Data>() }
    val adapter: PagingTestAdapter by lazy {
        PagingTestAdapter(object : DiffUtil.ItemCallback<Data>() {
            /**
             * Called to check whether two objects represent the same item.
             * <p>
             * For example, if your items have unique ids, this method should check their id equality.
             *
             * @param oldItem The item in the old list.
             * @param newItem The item in the new list.
             * @return True if the two items represent the same object or false if they are different.
             *
             * @see Callback#areItemsTheSame(int, int)
             */
            override fun areItemsTheSame(oldItem: Data?, newItem: Data?) = oldItem?.age ?: 0 == newItem?.age ?: 0

            /**
             * Called to check whether two items have the same data.
             * <p>
             * This information is used to detect if the contents of an item have changed.
             * <p>
             * This method to check equality instead of {@link Object#equals(Object)} so that you can
             * change its behavior depending on your UI.
             * <p>
             * For example, if you are using DiffUtil with a
             * {@link android.support.v7.widget.RecyclerView.Adapter RecyclerView.Adapter}, you should
             * return whether the items' visual representations are the same.
             * <p>
             * This method is called only if {@link #areItemsTheSame(T, T)} returns {@code true} for
             * these items.
             *
             * @param oldItem The item in the old list.
             * @param newItem The item in the new list.
             * @return True if the contents of the items are the same or false if they are different.
             *
             * @see Callback#areContentsTheSame(int, int)
             */
            override fun areContentsTheSame(oldItem: Data?, newItem: Data?) = oldItem?.age ?: 0 == newItem?.age ?: 0

            /**
             * When {@link #areItemsTheSame(T, T)} returns {@code true} for two items and
             * {@link #areContentsTheSame(T, T)} returns false for them, this method is called to
             * get a payload about the change.
             * <p>
             * For example, if you are using DiffUtil with {@link RecyclerView}, you can return the
             * particular field that changed in the item and your
             * {@link android.support.v7.widget.RecyclerView.ItemAnimator ItemAnimator} can use that
             * information to run the correct animation.
             * <p>
             * Default implementation returns {@code null}.
             *
             * @see Callback#getChangePayload(int, int)*/
            override fun getChangePayload(oldItem: Data?, newItem: Data?): Any {
                return super.getChangePayload(oldItem, newItem)
            }
        }, data).apply {
            submitList(pagedList)
        }

    }
    val dataSource: PositionalDataSource<Data> by lazy {
        object : PositionalDataSource<Data>() {
            /**
             * Called to load a range of data from the DataSource.
             * <p>
             * This method is called to load additional pages from the DataSource after the
             * LoadInitialCallback passed to dispatchLoadInitial has initialized a PagedList.
             * <p>
             * Unlike {@link #loadInitial(LoadInitialParams, LoadInitialCallback)}, this method must return
             * the number of items requested, at the position requested.
             *
             * @param params Parameters for load, including start position and load size.
             * @param callback Callback that receives loaded data.
             */
            override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Data>) {
                Log.e("PositionalDataSource", "loadRange--loadSize=" + params.loadSize.toString() + "；startPosition=" + params.startPosition.toString())
                callback.onResult(data)
            }

            /**
             * Load initial list data.
             * <p>
             * This method is called to load the initial page(s) from the DataSource.
             * <p>
             * Result list must be a multiple of pageSize to enable efficient tiling.
             *
             * @param params Parameters for initial load, including requested start position, load size, and
             *               page size.
             * @param callback Callback that receives initial load data, including
             *                 position and total data set size.
             */
            override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Data>) {
                callback.onResult(data, 0, data.size)
                Log.e("PositionalDataSource", "loadInitial--pageSize=" + params.pageSize.toString() + "；requestedStartPosition=" + params.requestedStartPosition.toString())
            }

        }

    }
    val pagedList: PagedList<Data> by lazy {
        PagedList.Builder(dataSource,
                PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(20)
                        .setPageSize(20)
                        .build())
                .setNotifyExecutor { it.run() }
                .setFetchExecutor { it.run() }
                .build()
    }
    val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this@MainActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        recycle_view.apply {

            layoutManager = linearLayoutManager
            adapter = this@MainActivity.adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    pagedList.loadAround(linearLayoutManager.findLastVisibleItemPosition())
                }
            })
        }


    }

    private fun initData() {
        for (i in 0 until 500) {
            data.add(Data("姓名$i", i))
        }
    }
}
