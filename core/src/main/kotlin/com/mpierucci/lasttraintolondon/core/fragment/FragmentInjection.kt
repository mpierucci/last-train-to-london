package com.mpierucci.lasttraintolondon.core.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.MapKey
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass


typealias FragmentsProviderMap = Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>


@Target(AnnotationTarget.FUNCTION)
@MapKey
annotation class FragmentClassKey(val value: KClass<out Fragment>)

@Reusable
class GenericFragmentFactory @Inject constructor(
    private val fragmentsProvider: FragmentsProviderMap
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        return fragmentsProvider[fragmentClass]?.get() ?: super.instantiate(classLoader, className)
    }
}
