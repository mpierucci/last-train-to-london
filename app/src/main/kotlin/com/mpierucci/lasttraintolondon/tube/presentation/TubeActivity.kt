package com.mpierucci.lasttraintolondon.tube.presentation

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.di.DaggerAppComponent
import com.mpierucci.lasttraintolondon.tube.di.LineStatusesModule
import com.mpierucci.lasttraintolondon.tube.domain.LineStatusRepository

import kotlinx.android.synthetic.main.activity_dummy.*
import javax.inject.Inject

class TubeActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: LineStatusRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerAppComponent.factory().create(applicationContext).plus(LineStatusesModule)
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
