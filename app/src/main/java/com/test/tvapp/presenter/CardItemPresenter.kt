package com.test.tvapp.presenter

import android.graphics.drawable.Drawable
import android.support.v17.leanback.widget.ImageCardView
import android.support.v17.leanback.widget.Presenter
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.test.tvapp.R
import com.test.tvapp.repository.model.AppInfo
import com.test.tvapp.repository.model.Video
import kotlin.properties.Delegates

class CardItemPresenter: Presenter() {

    companion object {
        private val CARD_WIDTH = 313
        private val CARD_HEIGHT = 176
    }

    private var defaultCardImage: Drawable? = null
    private var defaultBackgroundColor: Int by Delegates.notNull()
    private var selectedBackgroundColor: Int by Delegates.notNull()

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        defaultCardImage = ContextCompat.getDrawable(parent.context, R.drawable.movie)
        defaultBackgroundColor = ContextCompat.getColor(parent.context, R.color.default_background)
        selectedBackgroundColor = ContextCompat.getColor(parent.context, R.color.selected_background)

        val imageCardView = object: ImageCardView(parent.context) {
            override fun setSelected(selected: Boolean) {
                super.setSelected(selected)
                updateCardBackgroundColor(this, selected)
            }
        }
        imageCardView.isFocusable = true
        imageCardView.isFocusableInTouchMode = true
        updateCardBackgroundColor(imageCardView, false)

        return ViewHolder(imageCardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {

        if(item is Video) {
            val imageCardView = viewHolder.view as ImageCardView
            if(!TextUtils.isEmpty(item.cardImageUrl)) {
                imageCardView.titleText = item.title
                imageCardView.contentText = item.studio
                imageCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)

                val options = RequestOptions()
                        .centerCrop()
                        .error(defaultCardImage)

                Glide.with(viewHolder.view.context)
                        .load(item.cardImageUrl)
                        .apply(options)
                        .into(imageCardView.mainImageView)
            }
        } else if(item is AppInfo) {
            val imageCardView = viewHolder.view as ImageCardView
            if(!TextUtils.isEmpty(item.packageName)) {
                imageCardView.titleText = item.appName
                imageCardView.contentText = item.packageName
                imageCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
                imageCardView.mainImage = item.icon
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val imageCardView = viewHolder.view as ImageCardView
        imageCardView.badgeImage = null
        imageCardView.mainImage = null
    }

    private fun updateCardBackgroundColor(view: ImageCardView, selected: Boolean) {
        val color = if (selected) {
            selectedBackgroundColor
        } else {
            defaultBackgroundColor
        }
        view.setBackgroundColor(color)
        view.setInfoAreaBackgroundColor(color)
    }

}