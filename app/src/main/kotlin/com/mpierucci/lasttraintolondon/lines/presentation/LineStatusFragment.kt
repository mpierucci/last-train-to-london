package com.mpierucci.lasttraintolondon.lines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpierucci.lasttraintolondon.R
import com.mpierucci.lasttraintolondon.core.presentation.ViewContract
import com.mpierucci.lasttraintolondon.mvvm.viewModel
import kotlinx.android.synthetic.main.fragment_lines_status.*
import javax.inject.Inject
import javax.inject.Provider

class LineStatusFragment @Inject constructor(
    private val vmProvider: Provider<LinesStatusViewModel>
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lines_status, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel by viewModel { vmProvider.get() }

        viewModel.lineStatus.observe(viewLifecycleOwner, Observer {
            val statusAdapter = LineStatusAdapter()

            when (it) {
                is ViewContract.Success<List<PresentationLineStatus>> -> {
                    linesStatus.apply {
                        layoutManager = LinearLayoutManager(
                            requireActivity(),
                            RecyclerView.VERTICAL, false
                        )
                        addItemDecoration(LineStatusDecorator(R.dimen.grid_1))
                        adapter = statusAdapter
                    }
                    statusAdapter.submitList(it.result)
                }
                is ViewContract.Error -> {
                }
            }
        })
    }
}