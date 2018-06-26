package com.test.tvapp.ui.guidedstep.fragment

import android.os.Bundle
import android.support.v17.leanback.app.GuidedStepFragment
import android.support.v17.leanback.widget.GuidanceStylist
import android.support.v17.leanback.widget.GuidedAction
import com.test.tvapp.R
import com.test.tvapp.ui.guidedstep.GuidedStepActivity
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.OPTION_CHECKED
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.OPTION_DESCRIPTIONS
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.OPTION_DRAWABLES
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.OPTION_NAMES

class SecondStepFragment: GuidedStepFragment() {

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        val title = getString(R.string.guidedstep_second_title)
        val breadcrumb = getString(R.string.guidedstep_second_breadcrumb)
        val description = getString(R.string.guidedstep_second_description)
        val icon = activity.getDrawable(R.drawable.ic_main_icon)
        return GuidanceStylist.Guidance(title, description, breadcrumb, icon)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        val desc = resources.getString(R.string.guidedstep_action_description)
        actions.add(
                GuidedAction.Builder()
                .title(resources.getString(R.string.guidedstep_action_title))
                .description(desc)
                .multilineDescription(true)
                .infoOnly(true)
                .enabled(false)
                .build())
        for (i in 0 until OPTION_NAMES.size) {
            GuidedStepActivity.addCheckedAction(
                    actions,
                    OPTION_DRAWABLES[i],
                    activity,
                    OPTION_NAMES[i],
                    OPTION_DESCRIPTIONS[i],
                    OPTION_CHECKED[i])
        }
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        val fm = fragmentManager
        val next = ThirdStepFragment.newInstance(selectedActionPosition - 1)
        GuidedStepFragment.add(fm, next)
    }

}