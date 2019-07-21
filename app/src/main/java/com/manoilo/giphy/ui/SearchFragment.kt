package com.manoilo.giphy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.manoilo.giphy.R
import com.manoilo.giphy.binding.FragmentDataBindingComponent
import com.manoilo.giphy.databinding.SearchFragmentBinding
import com.manoilo.giphy.di.Injectable
import com.manoilo.giphy.ui.common.GifListAdapter
import com.manoilo.giphy.utils.AppExecutors
import javax.inject.Inject

class SearchFragment : Fragment(), Injectable {

    companion object {
        fun newInstance() = SearchFragment()
    }

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    private lateinit var binding: SearchFragmentBinding

    private lateinit var adapter: GifListAdapter

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_fragment,
            container,
            false,
            dataBindingComponent
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner

        val rvAdapter = GifListAdapter(
            dataBindingComponent = dataBindingComponent,
            appExecutors = appExecutors
        )
        binding.photoList.adapter = rvAdapter
        adapter = rvAdapter
    }
}
