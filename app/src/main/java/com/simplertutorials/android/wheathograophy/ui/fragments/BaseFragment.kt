package com.simplertutorials.android.wheathograophy.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewbinding.ViewBinding
import com.simplertutorials.android.wheathograophy.ui.ActivityCallback

abstract class BaseFragment<VIEWMODEL : BaseViewModel, BINDING : ViewBinding> : Fragment() {

    private var _binding: BINDING? = null
    private var _activityCallback: ActivityCallback? = null

    protected val B: BINDING
        get() = _binding!!
    protected val activityCallback: ActivityCallback
        get() = _activityCallback!!

    protected lateinit var viewModel: VIEWMODEL

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = generateViewModel()
    }

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return B.root
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        _activityCallback = context as ActivityCallback
    }

    @CallSuper
    override fun onDetach() {
        super.onDetach()
        _activityCallback = null
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING?
    abstract fun generateViewModel(): VIEWMODEL

    protected fun <T> LiveData<T>.observe(onNext: (T) -> Unit) {
        observe(viewLifecycleOwner) { onNext(it) }
    }
}
