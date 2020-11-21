package com.mpierucci.lasttraintolondon.lines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.android.architecture.viewmodel.viewModel
import com.mpierucci.lasttraintolondon.core.edgetoede.accountStatusBarSpace
import com.mpierucci.lasttraintolondon.core.failure.Failure
import com.mpierucci.lasttraintolondon.core.view.goneIfNot
import com.mpierucci.lasttraintolondon.core.view.visibleIfNot
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
        /*
        Initial offset it to below the status  bar, but enable edge to edge if scroll (clip to padding false on the recycler is  needed)
         */
        bindings.linesStatus.accountStatusBarSpace()
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

        bindings.swipeLayout
            .refreshes()
            .onEach { viewModel.postAction(LinesViewAction.FetchStatus) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        bindings.retryButton
            .clicks()
            .onEach { viewModel.postAction(LinesViewAction.FetchStatus) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindings = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.lineStatuses.collect {
                when (it) {
                    is LinesViewState.Success -> handleSuccessState(it)

                    is LinesViewState.Error -> handleError(it)

                    is LinesViewState.Loading -> handleLoadingState()
                }
            }
        }
    }

    private fun handleLoadingState() {
        val swipeLayout = bindings.swipeLayout
        if (!swipeLayout.isRefreshing) {
            bindings.progressBar.show()
        }
    }

    private fun handleSuccessState(state: LinesViewState.Success) {
        (bindings.linesStatus.adapter as LineStatusAdapter).submitList(state.result)
        bindings.progressBar.hide()
        bindings.errorGroup.goneIfNot()
        bindings.linesStatus.visibleIfNot()
        val swipeLayout = bindings.swipeLayout
        if (swipeLayout.isRefreshing) {
            swipeLayout.isRefreshing = false
        }
    }

    private fun handleError(state: LinesViewState.Error) {
        bindings.progressBar.hide()
        val swipeLayout = bindings.swipeLayout
        if (swipeLayout.isRefreshing) {
            swipeLayout.isRefreshing = false
        }
        bindings.linesStatus.goneIfNot()
        bindings.errorGroup.visibleIfNot()

        when (state.failure) {
            is Failure.NetworkConnection -> {
                bindings.errorMessage.text = getString(R.string.connection_error_message)
                bindings.retryButton.visibleIfNot()
            }
            else -> {
                bindings.errorMessage.text = getString(R.string.unhandled_error_message)
                bindings.retryButton.goneIfNot()
            }
        }
    }
}