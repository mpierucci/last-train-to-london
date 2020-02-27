package com.mpierucci.lasttraintolondon.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.core.fragment.GenericFragmentFactory
import com.mpierucci.lasttraintolondon.databinding.SingleActivityBinding
import com.mpierucci.lasttraintolondon.di.injector
import javax.inject.Inject

class SingleActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: GenericFragmentFactory
    lateinit var viewBinding: SingleActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        injector.singleActivityComponent().inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
        viewBinding = SingleActivityBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        val navigationController = Navigation.findNavController(
            this,
            R.id.linesNavigationHost
        )
        viewBinding.linesBottomNavigation.setupWithNavController(navigationController)
    }
}
