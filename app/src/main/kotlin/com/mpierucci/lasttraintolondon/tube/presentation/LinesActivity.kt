package com.mpierucci.lasttraintolondon.tube.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.core.fragment.GenericFragmentFactory
import com.mpierucci.lasttraintolondon.di.injector
import kotlinx.android.synthetic.main.activity_lines.*
import javax.inject.Inject

class LinesActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: GenericFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.plus().inject(this)
        supportFragmentManager.fragmentFactory = fragmentFactory
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
