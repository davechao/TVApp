package com.test.tvapp.ui.guidedstep.fragment

import android.os.Bundle
import android.support.v17.leanback.app.GuidedStepFragment
import android.support.v17.leanback.widget.GuidanceStylist
import android.support.v17.leanback.widget.GuidedAction
import com.test.tvapp.R
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.BACK
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.CONTINUE
import android.support.v17.leanback.widget.GuidanceStylist.Guidance
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.addAction

class FirstStepFragment: GuidedStepFragment() {

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        val title = getString(R.string.guidedstep_first_title)
        val breadcrumb = getString(R.string.guidedstep_first_breadcrumb)
        val description = getString(R.string.guidedstep_first_description)
        val icon = activity.getDrawable(R.drawable.ic_main_icon)
        return Guidance(title, description, breadcrumb, icon)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        addAction(actions,
                CONTINUE.toLong(),
                getString(R.string.guidedstep_continue),
                getString(R.string.guidedstep_letsdoit))
        addAction(actions,
                BACK.toLong(),
                getString(R.string.guidedstep_cancel),
                getString(R.string.guidedstep_nevermind))
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        val fm = fragmentManager
        if (action?.id === CONTINUE.toLong()) {
            GuidedStepFragment.add(fm, SecondStepFragment())
        } else {
            activity.finishAfterTransition()
        }
    }

}