package com.mpierucci.lasttraintolondon.tube.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.di.injector
import com.mpierucci.lasttraintolondon.mvvm.viewModel
import com.mpierucci.lasttraintolondon.tube.di.LineStatusesModule
import kotlinx.android.synthetic.main.activity_lines.*
import javax.inject.Inject
import javax.inject.Provider

class LinesActivity : AppCompatActivity() {

    @Inject
    lateinit var vmProvider: Provider<LinesStatusViewModel>

    private val viewModel by viewModel { vmProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.plus(LineStatusesModule)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lines)
        setSupportActionBar(toolbar)

        viewModel.lineStatus.observe(this, Observer {
            val adapter = LineStatusAdapter()
            statusList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            statusList.adapter = adapter
            adapter.submitList(it)
        })
    }
}
