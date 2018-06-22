package com.test.tvapp.ui.grid

import android.content.Intent
import android.os.Bundle
import android.support.v17.leanback.app.VerticalGridFragment
import android.support.v17.leanback.widget.*
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.test.tvapp.R
import com.test.tvapp.presenter.CardItemPresenter
import com.test.tvapp.repository.VideoRepository
import com.test.tvapp.repository.model.Video
import com.test.tvapp.ui.detail.DetailActivity

class VerticalGridsFragment: VerticalGridFragment() {

    companion object {
        private val NUM_COLUMNS = 5
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupListener()
        setupData()
    }

    private fun setupView() {
        val gridPresenter = VerticalGridPresenter()
        gridPresenter.numberOfColumns = NUM_COLUMNS
        setGridPresenter(gridPresenter)
        title = getString(R.string.grid_view)
        searchAffordanceColor = ContextCompat.getColor(activity, R.color.search_opaque)
    }

    private fun setupListener() {
        setOnSearchClickedListener {
            Toast.makeText(activity, "Implement your own in-app search", Toast.LENGTH_SHORT).show()
        }
        onItemViewClickedListener = ItemViewClickedListener()
        setOnItemViewSelectedListener(ItemViewSelectedListener())
    }

    private fun setupData() {
        var cardRowAdapter = ArrayObjectAdapter(CardItemPresenter())

        val videoRepository = VideoRepository()
        val videoCategorys = videoRepository.videoCategory
        val videoMap = videoRepository.getVideoMap()
        videoCategorys.forEachIndexed { index, it ->
            val videoList = videoMap[it]
            videoList?.shuffle()
            cardRowAdapter.addAll(index, videoList)
        }

        adapter = cardRowAdapter
    }

    private inner class ItemViewClickedListener: OnItemViewClickedListener {
        override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?) {
            if(item is Video) {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.KEY_VIDEO, item)
                startActivity(intent)
            }
        }
    }

    private inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?) {

        }
    }

}