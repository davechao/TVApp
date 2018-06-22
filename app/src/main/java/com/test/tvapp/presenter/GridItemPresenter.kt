package com.test.tvapp.presenter

import android.graphics.Color
import android.support.v17.leanback.widget.Presenter
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.test.tvapp.R

class GridItemPresenter: Presenter() {

    companion object {
        private val GRID_ITEM_WIDTH = 200
        private val GRID_ITEM_HEIGHT = 200
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = TextView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(GRID_ITEM_WIDTH, GRID_ITEM_HEIGHT)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundColor(ContextCompat.getColor(parent.context, R.color.default_background))
        view.setTextColor(Color.WHITE)
        view.gravity = Gravity.CENTER
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        (viewHolder.view as TextView).text = item as String
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {

    }

}