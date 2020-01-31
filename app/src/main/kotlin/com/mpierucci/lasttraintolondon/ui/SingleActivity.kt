package com.mpierucci.lasttraintolondon.ui

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.core.fragment.GenericFragmentFactory
import com.mpierucci.lasttraintolondon.di.injector
import kotlinx.android.synthetic.main.activity_lines.*
import javax.inject.Inject

class SingleActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: GenericFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        injector.plus().inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lines)
        setSupportActionBar(toolbar)
        val navigationController = Navigation.findNavController(
            this,
            R.id.linesNavigationHost
        )

        linesBottomNavigation.setupWithNavController(navigationController)
    }
}
