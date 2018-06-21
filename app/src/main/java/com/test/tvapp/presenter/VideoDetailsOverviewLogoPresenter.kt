package com.test.tvapp.presenter

import android.support.v17.leanback.widget.DetailsOverviewLogoPresenter
import android.support.v17.leanback.widget.Presenter
import android.view.ViewGroup

class VideoDetailsOverviewLogoPresenter: DetailsOverviewLogoPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {

        return super.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        super.onBindViewHolder(viewHolder, item)

    }
}