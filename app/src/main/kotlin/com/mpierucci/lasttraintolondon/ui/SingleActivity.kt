package com.mpierucci.lasttraintolondon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
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
        setContentView(bindings.root)

        val navigationController = Navigation.findNavController(
            this,
            R.id.linesNavigationHost
        )
        bindings.linesBottomNavigation.setupWithNavController(navigationController)
    }
}
