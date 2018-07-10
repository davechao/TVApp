package com.test.tvapp.ui.launcher

import android.content.Intent
import android.os.Bundle
import android.support.v17.leanback.app.VerticalGridFragment
import android.support.v17.leanback.widget.*
import android.support.v4.content.ContextCompat
import com.test.tvapp.R
import com.test.tvapp.presenter.CardItemPresenter
import com.test.tvapp.repository.model.AppInfo

class LauncherFragment: VerticalGridFragment() {

    companion object {
        private const val NUM_COLUMNS = 5
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
        gridPresenter.numberOfColumns = LauncherFragment.NUM_COLUMNS
        setGridPresenter(gridPresenter)
        title = getString(R.string.launcher)
        searchAffordanceColor = ContextCompat.getColor(activity, R.color.search_opaque)
    }

    private fun setupListener() {
        onItemViewClickedListener = ItemViewClickedListener()
        setOnItemViewSelectedListener(ItemViewSelectedListener())
    }

    private fun setupData() {
        var cardRowAdapter = ArrayObjectAdapter(CardItemPresenter())

        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)

        val packageManager = activity.packageManager
        val apps = packageManager.queryIntentActivities(intent, 0)
        apps.forEach {

            if(it.activityInfo.banner != 0) {
                val appInfo = AppInfo(
                        it.loadLabel(packageManager).toString(),
                        it.activityInfo.packageName,
                        it.activityInfo.loadBanner(packageManager))
                cardRowAdapter.add(appInfo)
            } else {
                val appInfo = AppInfo(
                        it.loadLabel(packageManager).toString(),
                        it.activityInfo.packageName,
                        it.activityInfo.loadIcon(packageManager))
                cardRowAdapter.add(appInfo)
            }
        }
        adapter = cardRowAdapter
    }

    private inner class ItemViewClickedListener: OnItemViewClickedListener {
        override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder?,
                item: Any?,
                rowViewHolder: RowPresenter.ViewHolder?,
                row: Row?) {
            if(item is AppInfo) {
                val intent = activity.packageManager.getLaunchIntentForPackage(item.packageName)
                activity.startActivity(intent)
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