package com.maxnovikov.filmapplication.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.maxnovikov.filmapplication.R

fun FragmentManager.navigateTo(fragment: Fragment) {
    beginTransaction()
        .setCustomAnimations(
            R.anim.slide_from_right,
            R.anim.slide_to_left,
            R.anim.slide_from_left,
            R.anim.slide_to_right
        )
        .replace(R.id.mainContainer, fragment, fragment::class.java.canonicalName)
        .addToBackStack(fragment::class.java.canonicalName)
        .commitAllowingStateLoss()
}
