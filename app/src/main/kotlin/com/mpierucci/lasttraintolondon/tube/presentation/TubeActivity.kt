package com.mpierucci.lasttraintolondon.tube.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.di.injector
import com.mpierucci.lasttraintolondon.mvvm.viewModel
import com.mpierucci.lasttraintolondon.tube.di.LineStatusesModule
import kotlinx.android.synthetic.main.activity_dummy.*
import javax.inject.Inject
import javax.inject.Provider

class TubeActivity : AppCompatActivity() {

    @Inject
    lateinit var vmProvider: Provider<TubeStatusViewModel>

    val viewModel by viewModel { vmProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.plus(LineStatusesModule)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}
