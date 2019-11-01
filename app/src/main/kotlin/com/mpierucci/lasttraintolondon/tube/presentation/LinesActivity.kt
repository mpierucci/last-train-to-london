package com.mpierucci.lasttraintolondon.tube.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.core.fragment.fragmentFactory
import com.mpierucci.lasttraintolondon.di.injector
import com.mpierucci.lasttraintolondon.mvvm.viewModel
import com.mpierucci.lasttraintolondon.tube.di.LineStatusesModule
import kotlinx.android.synthetic.main.activity_lines.*
import javax.inject.Inject
import javax.inject.Provider

class LinesActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentProvider: Provider<LineStatusFragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory { fragmentProvider.get() }
        injector.plus(LineStatusesModule)
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lines)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                val fragment = supportFragmentManager.fragmentFactory.instantiate(
                    classLoader,
                    LineStatusFragment::class.java.canonicalName.orEmpty()
                )
                add(R.id.lineFragmentContainer, fragment)
            }
        }
    }
}
