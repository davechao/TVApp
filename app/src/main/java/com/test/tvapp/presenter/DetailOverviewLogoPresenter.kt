package com.test.tvapp.presenter

import android.support.v17.leanback.widget.DetailsOverviewLogoPresenter
import android.support.v17.leanback.widget.DetailsOverviewRow
import android.support.v17.leanback.widget.Presenter
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.test.tvapp.R
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter
import android.view.View

class DetailOverviewLogoPresenter: DetailsOverviewLogoPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        val imageView = LayoutInflater.from(parent.context)
                .inflate(R.layout.lb_fullwidth_details_overview_logo, parent, false) as ImageView
        val width = parent.resources.getDimensionPixelSize(R.dimen.detail_thumb_width)
        val height = parent.resources.getDimensionPixelSize(R.dimen.detail_thumb_height)
        imageView.layoutParams = ViewGroup.MarginLayoutParams(width, height)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any) {
        val row = item as DetailsOverviewRow
        val imageView = viewHolder.view as ImageView
        imageView.setImageDrawable(row.imageDrawable)
        if (isBoundToImage(viewHolder as ViewHolder, row)) {
            viewHolder.parentPresenter.notifyOnBindLogo(viewHolder.parentViewHolder)
        }
    }

    internal class ViewHolder(view: View): DetailsOverviewLogoPresenter.ViewHolder(view) {
        override fun getParentPresenter(): FullWidthDetailsOverviewRowPresenter {
            return mParentPresenter
        }

        override fun getParentViewHolder(): FullWidthDetailsOverviewRowPresenter.ViewHolder {
            return mParentViewHolder
        }
    }
}