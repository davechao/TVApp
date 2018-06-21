package com.test.tvapp.presenter

import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter
import com.test.tvapp.repository.model.Video

class DetailsDescriptionPresenter: AbstractDetailsDescriptionPresenter() {

    override fun onBindDescription(viewHolder: ViewHolder, item: Any?) {
        if(item is Video) {
            viewHolder.title.text = item.title
            viewHolder.subtitle.text = item.studio
            viewHolder.body.text = item.description
        }
    }

}