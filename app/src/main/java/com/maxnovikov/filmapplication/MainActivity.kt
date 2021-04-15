package com.maxnovikov.filmapplication

import android.os.Bundle

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = TopFilmFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fragment, fragment::class.java.canonicalName)
                .commitAllowingStateLoss()
        }
    }
}