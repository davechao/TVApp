package com.test.tvapp.presenter

import android.content.Context
import android.support.v17.leanback.widget.Presenter
import android.support.v17.leanback.widget.RowHeaderPresenter
import android.view.LayoutInflater
import android.view.ViewGroup
import com.test.tvapp.R
import android.support.v17.leanback.widget.ListRow
import android.widget.ImageView
import android.widget.TextView
import kotlin.properties.Delegates

class IconHeaderItemPresenter: RowHeaderPresenter() {

    private var unselectedAlpha: Float by Delegates.notNull()

    override fun onCreateViewHolder(parent: ViewGroup): Presenter.ViewHolder {
        unselectedAlpha = parent.resources
                .getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1)
        val inflater = parent.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view = inflater.inflate(R.layout.item_icon_header, null)
        view.alpha = unselectedAlpha

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any, payloads: MutableList<Any>) {
        super.onBindViewHolder(viewHolder, item, payloads)

        val rootView = viewHolder.view
        val iconView = rootView.findViewById<ImageView>(R.id.header_icon)
        val label = rootView.findViewById<TextView>(R.id.header_label)
        rootView.isFocusable = true

        val icon = rootView.resources.getDrawable(R.drawable.android_header, null)
        iconView.setImageDrawable(icon)

        val headerItem = (item as ListRow).headerItem
        label.text = headerItem.name
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        super.onUnbindViewHolder(viewHolder)
    }

    override fun onSelectLevelChanged(holder: ViewHolder) {
        holder.view.alpha = unselectedAlpha + holder.selectLevel *
                (1.0f - unselectedAlpha)
    }
}