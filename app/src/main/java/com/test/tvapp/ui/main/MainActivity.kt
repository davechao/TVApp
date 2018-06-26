
package com.test.tvapp.ui.main

import android.app.Activity
import android.os.Bundle
import com.test.tvapp.R
import android.preference.PreferenceManager
import com.test.tvapp.ui.onboarding.OnboardingsFragment
import com.test.tvapp.ui.onboarding.OnboardingActivity
import android.content.Intent

class MainActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        if(!sharedPreferences.getBoolean(OnboardingsFragment.COMPLETED_ONBOARDING, false)) {
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
        }
    }
}
