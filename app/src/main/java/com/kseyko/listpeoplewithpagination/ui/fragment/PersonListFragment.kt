package com.kseyko.listpeoplewithpagination.ui.fragment

import androidx.fragment.app.viewModels
import com.kseyko.listpeoplewithpagination.R
import com.kseyko.listpeoplewithpagination.databinding.FragmentPersonListBinding
import com.kseyko.listpeoplewithpagination.enums.ErrorTypes
import com.kseyko.listpeoplewithpagination.ui.base.BaseFragment
import com.kseyko.listpeoplewithpagination.ui.recyclerviews.adapters.PersonListAdapter
import com.kseyko.listpeoplewithpagination.ui.recyclerviews.listener.PaginationScrollListener
import com.kseyko.listpeoplewithpagination.ui.viewmodels.PersonListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PersonListFragment :
    BaseFragment<FragmentPersonListBinding, PersonListViewModel>(FragmentPersonListBinding::inflate) {

    override val viewModel: PersonListViewModel by viewModels()

    private var personListAdapter: PersonListAdapter = PersonListAdapter()

    override fun initViews() {

        _binding.mAdapter = personListAdapter

        _binding.personListRecyclerView.addOnScrollListener(object : PaginationScrollListener() {
            override fun onScrollLimit() {
                personListAdapter.addLoadingFooter()
                viewModel.loadNextPage()
                personListAdapter.removeLoadingFooter()
            }
        })

        _binding.pullToRefreshLayout.setOnRefreshListener {
            viewModel.refreshPage()
            _binding.pullToRefreshLayout.isRefreshing = false
        }
    }

    override fun setObservers() {
        viewModel.personList.observe(this) {
            if (!it.isNullOrEmpty()) {
                personListAdapter.setItems(ArrayList(it))
                if (personListAdapter.itemCount < 19) {
                    viewModel.loadNextPage()
                }
            } else if (it != null) {
                errorStatusChanged(ErrorTypes.NO_DATA)
            }
        }
        viewModel.isProgressVisible.observe(this) {
            _binding.isVisibleProgress = it
        }
        viewModel.errors.observe(this) {
            errorStatusChanged(it)
        }
    }

    private fun errorStatusChanged(error: ErrorTypes) {
        when (error) {
            ErrorTypes.NO_DATA -> showToast(getString(R.string.no_data))
            ErrorTypes.NO_NEW_ITEM -> showToast(getString(R.string.no_new_item))
            ErrorTypes.INTERNAL_SERVER_ERROR -> showToast(getString(R.string.internal_server_error))
            ErrorTypes.PARAMETER_ERROR -> showToast(getString(R.string.parameter_error))
            else -> showToast(getString(R.string.error_somenting_wrong))
        }
        personListAdapter.removeLoadingFooter()

    }

}