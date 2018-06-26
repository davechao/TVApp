package com.test.tvapp.ui.guidedstep

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.test.tvapp.R
import android.support.v17.leanback.app.GuidedStepFragment
import android.support.v17.leanback.widget.GuidedAction
import com.test.tvapp.ui.guidedstep.fragment.FirstStepFragment

class GuidedStepActivity: Activity() {

    companion object {
        const val CONTINUE = 0
        const val BACK = 1
        private const val OPTION_CHECK_SET_ID = 10

        val OPTION_NAMES = arrayOf(
                "Option A",
                "Option B",
                "Option C")

        val OPTION_DESCRIPTIONS = arrayOf(
                "Here's one thing you can do",
                "Here's another thing you can do",
                "Here's one more thing you can do")

        val OPTION_DRAWABLES = intArrayOf(
                R.drawable.ic_guidedstep_option_a,
                R.drawable.ic_guidedstep_option_b,
                R.drawable.ic_guidedstep_option_c)

        val OPTION_CHECKED = booleanArrayOf(
                true,
                false,
                false)

        fun addAction(actions: MutableList<GuidedAction>,
                      id: Long,
                      title: String,
                      desc: String) {
            actions.add(GuidedAction.Builder()
                    .id(id)
                    .title(title)
                    .description(desc)
                    .build())
        }

        fun addCheckedAction(actions: MutableList<GuidedAction>,
                             iconResId: Int,
                             context: Context,
                             title: String,
                             desc: String,
                             checked: Boolean) {
            val guidedAction = GuidedAction.Builder()
                    .title(title)
                    .description(desc)
                    .checkSetId(OPTION_CHECK_SET_ID)
                    .iconResourceId(iconResId, context)
                    .build()
            guidedAction.isChecked = checked
            actions.add(guidedAction)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GuidedStepFragment.addAsRoot(this, FirstStepFragment(), android.R.id.content)
    }

}