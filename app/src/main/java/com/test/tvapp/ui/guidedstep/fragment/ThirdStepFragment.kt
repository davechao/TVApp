package com.test.tvapp.ui.guidedstep.fragment

import android.support.v17.leanback.app.GuidedStepFragment
import android.os.Bundle
import android.support.v17.leanback.widget.GuidanceStylist
import android.support.v17.leanback.widget.GuidedAction
import com.test.tvapp.R
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.BACK
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.CONTINUE
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.OPTION_NAMES
import com.test.tvapp.ui.guidedstep.GuidedStepActivity.Companion.addAction

class ThirdStepFragment: GuidedStepFragment() {

    companion object {
        const val ARG_OPTION_IDX = "arg.option.idx"

        fun newInstance(option: Int): ThirdStepFragment {
            val thirdStepFragment = ThirdStepFragment()
            val args = Bundle()
            args.putInt(ARG_OPTION_IDX, option)
            thirdStepFragment.arguments = args
            return thirdStepFragment
        }
    }

    override fun onCreateGuidance(savedInstanceState: Bundle?): GuidanceStylist.Guidance {
        val title = getString(R.string.guidedstep_third_title)
        val breadcrumb = getString(R.string.guidedstep_third_breadcrumb)
        val description = getString(R.string.guidedstep_third_command) +
                OPTION_NAMES[arguments.getInt(ARG_OPTION_IDX)]
        val icon = activity.getDrawable(R.drawable.ic_main_icon)
        return GuidanceStylist.Guidance(title, description, breadcrumb, icon)
    }

    override fun onCreateActions(actions: MutableList<GuidedAction>, savedInstanceState: Bundle?) {
        addAction(actions, CONTINUE.toLong(), "Done", "All finished")
        addAction(actions, BACK.toLong(), "Back", "Forgot something...")
    }

    override fun onGuidedActionClicked(action: GuidedAction?) {
        if (action?.id == CONTINUE.toLong()) {
            activity.finishAfterTransition()
        } else {
            fragmentManager.popBackStack()
        }
    }

}