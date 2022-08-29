package com.kseyko.listpeoplewithpagination.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.kseyko.listpeoplewithpagination.extensions.toast

/** Base class for Fragments that use the NavigationHandler in the hosting Activity*/
abstract class BaseFragment<T : ViewDataBinding, VM : BaseViewModel>(private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    Fragment() {

    lateinit var _binding: T

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateMethod.invoke(layoutInflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObservers()
    }

    protected abstract fun initViews()

    protected abstract fun setObservers()

    protected fun showToast(msg: String) {
        context?.toast(message = msg)
    }

}