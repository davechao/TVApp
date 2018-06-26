package com.test.tvapp.ui.onboarding

import android.animation.*
import android.os.Bundle
import android.support.v17.leanback.app.OnboardingFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.tvapp.R
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import android.graphics.drawable.AnimationDrawable

class OnboardingsFragment: OnboardingFragment() {

    companion object {
        const val COMPLETED_ONBOARDING = "COMPLETED_ONBOARDING"
        private const val ANIMATION_DURATION = 500
    }

    private val pageTitles = intArrayOf(
            R.string.onboarding_title_welcome,
            R.string.onboarding_title_design,
            R.string.onboarding_title_simple,
            R.string.onboarding_title_project)

    private val pageDescriptions = intArrayOf(
            R.string.onboarding_description_welcome,
            R.string.onboarding_description_design,
            R.string.onboarding_description_simple,
            R.string.onboarding_description_project)

    private val pageImages = intArrayOf(
            R.drawable.tv_animation_a,
            R.drawable.tv_animation_b,
            R.drawable.tv_animation_c,
            R.drawable.tv_animation_d)

    private lateinit var animator: Animator
    private lateinit var imageView: ImageView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        logoResourceId = R.drawable.videos_by_google_banner
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onFinishFragment() {
        super.onFinishFragment()
        val sharedPreferencesEditor = PreferenceManager.getDefaultSharedPreferences(activity).edit()
        sharedPreferencesEditor.putBoolean(COMPLETED_ONBOARDING, true)
        sharedPreferencesEditor.apply()
        activity.finish()
    }

    override fun getPageTitle(pageIndex: Int): CharSequence {
        return getString(pageTitles[pageIndex])
    }

    override fun getPageDescription(pageIndex: Int): CharSequence {
        return getString(pageDescriptions[pageIndex])
    }

    override fun getPageCount(): Int {
        return pageTitles.size
    }

    override fun onCreateForegroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        return null
    }

    override fun onCreateBackgroundView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        val bgView = View(activity)
        bgView.setBackgroundColor(ContextCompat.getColor(activity, R.color.fastlane_background))
        return bgView
    }

    override fun onCreateContentView(inflater: LayoutInflater?, container: ViewGroup?): View? {
        imageView = ImageView(activity)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.setPadding(0, 32, 0, 32)
        return imageView
    }

    override fun onPageChanged(newPage: Int, previousPage: Int) {
        super.onPageChanged(newPage, previousPage)
        if(animator != null) {
            animator.end()
        }

        val animators = arrayListOf<Animator>()
        val fadeOut = createFadeOutAnimator(imageView)
        fadeOut.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                imageView.setImageDrawable(ContextCompat.getDrawable(activity, pageImages[newPage]))
                (imageView.drawable as AnimationDrawable).start()
            }
        })
        animators.add(fadeOut)
        animators.add(createFadeInAnimator(imageView))
        val set = AnimatorSet()
        set.playSequentially(animators)
        set.start()
        animator = set
    }

    override fun onCreateEnterAnimation(): Animator? {
        imageView.setImageDrawable(ContextCompat.getDrawable(activity, pageImages[0]))
        (imageView.drawable as AnimationDrawable).start()
        animator = createFadeInAnimator(imageView)
        return animator
    }

    private fun createFadeInAnimator(view: View): Animator {
        return ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f).setDuration(ANIMATION_DURATION.toLong())
    }

    private fun createFadeOutAnimator(view: View): Animator {
        return ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f, 0.0f).setDuration(ANIMATION_DURATION.toLong())
    }

}