package com.mpierucci.lasttraintolondon.lines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.idling.CountingIdlingResource
import com.mpierucci.android.architecture.viewmodel.viewModel
import com.mpierucci.lasttraintolondon.lines.R
import com.mpierucci.lasttraintolondon.lines.databinding.FragmentLinesStatusBinding
import com.mpierucci.lasttraintolondon.lines.presentation.LinesStatusViewModel
import com.mpierucci.lasttraintolondon.lines.presentation.LinesViewAction
import com.mpierucci.lasttraintolondon.lines.presentation.LinesViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Provider

class LineStatusScreen @Inject constructor(
    private val vmProvider: Provider<LinesStatusViewModel>
) : Fragment() {

    //TODO https://github.com/mpierucci/last-train-to-london/issues/37
    val idlingResource = CountingIdlingResource("linesScreen")
    private var _bindings: FragmentLinesStatusBinding? = null
    private val bindings get() = _bindings!!
    private val viewModel by viewModel { vmProvider.get() }

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
            addItemDecoration(LineStatusDecorator(R.dimen.grid_1))
            adapter = LineStatusAdapter()
        }

        if (savedInstanceState == null) {
            viewModel.postAction(LinesViewAction.FetchStatus)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindings = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        idlingResource.increment()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.lineStatuses.collect {
                when (it) {
                    is LinesViewState.Success -> {
                        handleSuccessState(it)
                    }
                    is LinesViewState.Error -> {
                        idlingResource.decrement()
                    }
                    is LinesViewState.Loading -> {
                        handleLoadingState()
                    }
                }
            }
        }
        bindings.swipeLayout
            .refreshes()
            .onEach {
                idlingResource.increment()
                viewModel.postAction(LinesViewAction.FetchStatus)
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleLoadingState() {
        val swipeLayout = bindings.swipeLayout
        if (!swipeLayout.isRefreshing) {
            bindings.progressBar.show()
        }
    }

    private fun handleSuccessState(it: LinesViewState.Success) {
        (bindings.linesStatus.adapter as LineStatusAdapter).submitList(it.result)
        bindings.linesStatus.visibility = View.VISIBLE
        bindings.progressBar.hide()
        val swipeLayout = bindings.swipeLayout
        if (swipeLayout.isRefreshing) {
            swipeLayout.isRefreshing = false
        }
        idlingResource.decrement()
    }
}