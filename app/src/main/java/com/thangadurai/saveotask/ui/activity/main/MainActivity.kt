package com.thangadurai.saveotask.ui.activity.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.thangadurai.saveotask.R
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponseItem
import com.thangadurai.saveotask.data.network.Status
import com.thangadurai.saveotask.databinding.ActivityMainBinding
import com.thangadurai.saveotask.extensions.hide
import com.thangadurai.saveotask.extensions.show
import com.thangadurai.saveotask.ui.activity.base.BaseActivity
import com.thangadurai.saveotask.ui.activity.detail.DetailActivity
import com.thangadurai.saveotask.utils.RecyclerViewCallBack
import com.thangadurai.saveotask.utils.adapters.MyAdapter
import com.thangadurai.saveotask.utils.adapters.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class MainActivity : BaseActivity(), RecyclerViewCallBack {

    private lateinit var binding: ActivityMainBinding
    lateinit var myAdapter: MyAdapter
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerSetupAndChangeTitle()
        getPhotosObserver()

        mainViewModel.getPhotosId()

    }


    private fun getPhotosObserver() {
        mainViewModel.apiResponseAlbum.observe(this, { result ->
            result?.status?.let {
                when (it) {
                    Status.SUCCESS -> {
                        hideProgress()
                        binding.llData.show()

                        result.data?.let { data ->
                            if (data.isEmpty()) {
                                binding.llData.hide()
                                showToast("No data found")
                            }
                            myAdapter.addItems(data.take(50) as ArrayList<BasePhotoResponseItem>)
                            viewPagerSetup(data.take(5) as MutableList)
                            binding.rvMovieList.scheduleLayoutAnimation()
                        }
                    }
                    Status.ERROR -> {
                        binding.llData.hide()
                        hideProgress()
                        showToast("${result.message} Please try again later!")
                    }
                    Status.LOADING -> {
                        showProgress()
                    }

                }

            }

        })
    }

    private fun viewPagerSetup(list: MutableList<BasePhotoResponseItem>) {

        val effect = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
        }

        binding.vpMovieThumbnail.apply {
            adapter = ViewPagerAdapter(this@MainActivity, list)
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            get(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer(effect)
        }


    }

    private fun recyclerSetupAndChangeTitle() {
        myAdapter = MyAdapter(this, arrayListOf(), this)
        binding.rvMovieList.apply {
            this.adapter = myAdapter
            isNestedScrollingEnabled = false
        }

        binding.svRoot.apply {
            viewTreeObserver.addOnScrollChangedListener {
                val scrollY: Int = this.scrollY
                title = if (scrollY > 400) {
                    getString(R.string.txt_now_showing)
                } else {
                    getString(R.string.txt_movies)
                }
            }
        }

    }

    override fun onItemClick(view: View, model: Any, position: Int) {
        Intent(this, DetailActivity::class.java).apply {
            val options = ActivityOptions
                .makeSceneTransitionAnimation(
                    this@MainActivity,
                    view,
                    getString(R.string.txt_transition_name)
                )
            model as BasePhotoResponseItem
            this.putExtra(DetailActivity.DETAILS, model)
            startActivity(this, options.toBundle())
        }

    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val item = menu.findItem(R.id.menu)
        item.setIcon(R.drawable.ic_search)
        invalidateOptionsMenu()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu -> {
                showToast("Search")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}