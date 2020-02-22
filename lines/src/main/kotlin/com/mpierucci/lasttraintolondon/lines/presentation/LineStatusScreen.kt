package com.mpierucci.lasttraintolondon.lines.presentation

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
import kotlinx.android.synthetic.main.fragment_lines_status.*
import javax.inject.Inject
import javax.inject.Provider

class LineStatusScreen @Inject constructor(
    private val vmProvider: Provider<LinesStatusViewModel>
) : Fragment() {

    //TODO https://github.com/mpierucci/last-train-to-london/issues/37
    val idlingResource = CountingIdlingResource("linesScreen")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lines_status, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(linesStatus) {
            layoutManager = LinearLayoutManager(
                requireActivity(),
                RecyclerView.VERTICAL, false
            )
            addItemDecoration(LineStatusDecorator(R.dimen.grid_1))
            adapter = LineStatusAdapter()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModel { vmProvider.get() }

        idlingResource.increment()
        viewModel.lineStatuses.observe(viewLifecycleOwner) {
            when (it) {
                is ViewContract.Success<List<PresentationLineStatus>> -> {
                    (linesStatus.adapter as LineStatusAdapter).submitList(it.result)
                    idlingResource.decrement()
                }
                is ViewContract.Error -> {
                    idlingResource.decrement()
                }
            }

        }
    }
}