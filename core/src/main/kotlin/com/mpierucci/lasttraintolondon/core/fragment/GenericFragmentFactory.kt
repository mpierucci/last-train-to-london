package com.mpierucci.lasttraintolondon.core.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory


inline fun <reified T : Fragment> FragmentActivity.fragmentFactory(
    crossinline fragmentProvider:  () -> T
) = object : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            T::class.java.canonicalName -> fragmentProvider.invoke()
            else -> super.instantiate(classLoader, className)
        }
    }
}