package com.test.tvapp.ui.search

import android.Manifest.permission.RECORD_AUDIO
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.support.v17.leanback.app.SearchFragment
import android.content.pm.PackageManager
import android.support.v17.leanback.widget.*
import android.content.ActivityNotFoundException
import android.content.Intent
import android.text.TextUtils
import android.view.View
import com.test.tvapp.R
import com.test.tvapp.repository.VideoRepository
import android.support.v17.leanback.widget.HeaderItem
import com.test.tvapp.presenter.CardItemPresenter
import com.test.tvapp.repository.model.Video
import com.test.tvapp.ui.detail.DetailActivity

class SearchsFragment: SearchFragment(), SearchFragment.SearchResultProvider {

    companion object {
        private const val REQUEST_SPEECH = 0x00000010
        private const val FINISH_ON_RECOGNIZER_CANCELED = true
    }

    private lateinit var listRowAdapter: ArrayObjectAdapter
    private val resultsFound = false
    private lateinit var videoList: ArrayList<Video>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupPermission()
        setupData()
    }

    override fun getResultsAdapter(): ObjectAdapter {
        return listRowAdapter
    }

    override fun onQueryTextChange(newQuery: String?): Boolean {
        loadQuery(newQuery)
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        loadQuery(query)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_SPEECH -> {
                when(resultCode) {
                    RESULT_OK -> {
                        setSearchQuery(data, true)
                    } else -> {
                        if (FINISH_ON_RECOGNIZER_CANCELED
                                && !hasResults()) {
                            view.findViewById<View>(R.id.lb_search_bar_speech_orb).requestFocus()
                        }
                    }
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupView() {
        listRowAdapter = ArrayObjectAdapter(ListRowPresenter())
        setSearchResultProvider(this)
        setOnItemViewClickedListener(ItemViewClickedListener())
    }

    private fun setupPermission() {
        if (!hasPermission(RECORD_AUDIO)) {
            setSpeechRecognitionCallback {
                try {
                    startActivityForResult(recognizerIntent, REQUEST_SPEECH)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun setupData() {
        val videoRepository = VideoRepository()
        videoList = videoRepository.getVideoList()
    }

    private fun loadQuery(query: String?) {
        listRowAdapter.clear();
        if (!TextUtils.isEmpty(query)) {
            val header = HeaderItem(0, getString(R.string.search_result, query))
            var cardRowAdapter = ArrayObjectAdapter(CardItemPresenter())
            videoList.forEach {
                if(it.title?.toLowerCase()!!.contains(query.toString().toLowerCase())) {
                    cardRowAdapter.add(it)
                }
            }
            listRowAdapter.add(ListRow(header, cardRowAdapter))
        }
    }

    private fun hasPermission(permission: String): Boolean {
        return PackageManager.PERMISSION_GRANTED ==
                activity.packageManager.checkPermission(permission, activity.packageName)
    }

    fun hasResults(): Boolean {
        return listRowAdapter.size() > 0 && resultsFound
    }

    fun focusOnSearch() {
        view.findViewById<View>(R.id.lb_search_bar).requestFocus()
    }

    private inner class ItemViewClickedListener: OnItemViewClickedListener {
        override fun onItemClicked(itemViewHolder: Presenter.ViewHolder?,
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

}