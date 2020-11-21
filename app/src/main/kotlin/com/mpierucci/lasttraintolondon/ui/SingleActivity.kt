package com.mpierucci.lasttraintolondon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.core.fragment.GenericFragmentFactory
import com.mpierucci.lasttraintolondon.databinding.ActivityLinesBinding
import com.mpierucci.lasttraintolondon.di.injector
import javax.inject.Inject

class SingleActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: GenericFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        injector.plus().inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory

        super.onCreate(savedInstanceState)
        val bindings = ActivityLinesBinding.inflate(layoutInflater)
        // is bottom nav handled by default fby the framework? looks like
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContentView(bindings.root)


        /*
        It is not possible for the view of the NavHostFragment to be available in the onCreate() of the Activity.
        (the Activity's onCreate() is not where you should be accessing the Fragment's views)
        https://issuetracker.google.com/issues/142847973
         */
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.linesNavigationHost) as NavHostFragment
        val navController = navHostFragment.navController

        bindings.linesBottomNavigation.setupWithNavController(navController)
    }
}
