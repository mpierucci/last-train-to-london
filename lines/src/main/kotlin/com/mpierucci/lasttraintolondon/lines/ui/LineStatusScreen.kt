package com.mpierucci.lasttraintolondon.lines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.mpierucci.android.architecture.viewmodel.viewModel
import com.mpierucci.lasttraintolondon.core.presentation.ViewContract
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.databinding.FragmentLinesStatusBinding
import com.mpierucci.lasttraintolondon.lines.presentation.LinesStatusViewModel
import com.mpierucci.lasttraintolondon.lines.presentation.PresentationLineStatus
import javax.inject.Inject
import javax.inject.Provider

class LineStatusScreen @Inject constructor(
    private val vmProvider: Provider<LinesStatusViewModel>
) : Fragment() {

    //TODO https://github.com/mpierucci/last-train-to-london/issues/37
    val idlingResource = CountingIdlingResource("linesScreen")
    private var _bindings: FragmentLinesStatusBinding? = null
    private val bindings get() = _bindings!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindings = FragmentLinesStatusBinding.inflate(inflater, container, false)
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bindings.linesStatus) {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                RecyclerView.VERTICAL, false
            )
            addItemDecoration(
                LineStatusDecorator(
                    R.dimen.grid_1
                )
            )
            adapter =
                LineStatusAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindings = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModel { vmProvider.get() }

        idlingResource.increment()
        viewModel.lineStatuses.observe(viewLifecycleOwner) {
            when (it) {
                is ViewContract.Success<List<PresentationLineStatus>> -> {
                    (bindings.linesStatus.adapter as LineStatusAdapter).submitList(it.result)
                    idlingResource.decrement()
                }
                is ViewContract.Error -> {
                    idlingResource.decrement()
                }
            }

        }
    }
}