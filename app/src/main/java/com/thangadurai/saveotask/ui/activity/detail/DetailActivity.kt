package com.thangadurai.saveotask.ui.activity.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import com.thangadurai.saveotask.R
import com.thangadurai.saveotask.data.model.photo.BasePhotoResponseItem
import com.thangadurai.saveotask.databinding.ActivityDetailBinding
import com.thangadurai.saveotask.extensions.setImage
import com.thangadurai.saveotask.ui.activity.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()


    companion object {
        const val DETAILS = "DETAILS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.detailsViewModel = detailViewModel
        binding.listener = this

        intent?.extras?.getParcelable<BasePhotoResponseItem>(DETAILS)?.let {
            setImage(
                this,
                "${it.thumbnailUrl}.jpg",
                binding.ivMovie
            )
            detailViewModel.settingValue(it)
        }

        init()


    }

    private fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        title = getString(R.string.txt_movies)
        invalidateOptionsMenu()


        startAnimation(binding.tvMovieName, R.anim.right_to_left)
        startAnimation(binding.tvMovieDes, R.anim.right_to_left)
        startAnimation(binding.chipsMovieCategory, R.anim.right_to_left)
        startAnimation(binding.tvRating, R.anim.right_to_left)
        startAnimation(binding.rtMovie, R.anim.right_to_left)
        startAnimation(binding.tvMovieReview, R.anim.right_to_left)
        startAnimation(binding.btnBookNow, R.anim.right_to_left)

        startAnimation(binding.tvMovieSynopsis, R.anim.down_to_up)
        startAnimation(binding.tvMovieSynopsisDes, R.anim.down_to_up)
    }


    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        val item = menu.findItem(R.id.menu)
        item.setIcon(R.drawable.ic_share)

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                ActivityCompat.finishAfterTransition(this)
                true
            }
            R.id.menu -> {
                showToast("Share")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View) {
        when (v) {
            binding.btnBookNow -> {
                showToast("Booked")
            }
        }
    }

}