package com.matchmate.app.presentation.ui.fragments

import CloseClickListener
import MatchAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.matchmate.app.core.utils.MatchStatus
import com.matchmate.app.databinding.FragmentFirstBinding
import com.matchmate.app.domain.model.Person
import com.matchmate.app.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    private val adapter: MatchAdapter by lazy {
        MatchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
        setViews()
    }

    fun setViews(){
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        adapter.onMessageClicked = ::onMessageClicked
        adapter.onLikeClicked = ::onLikeClicked
        adapter.onCloseClicked = ::onCloseClicked
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerView)

        addScrollListener()
    }

    private fun onMessageClicked(data: Person) {
    }
    private fun onLikeClicked(data: Person, position: Int) {
        Toast.makeText(requireContext(), "Liked ${data.fullName}", Toast.LENGTH_SHORT).show()
        viewModel.updatePerson(data.apply { status = MatchStatus.ACCEPTED })
        adapter.notifyItemChanged(position)
    }
    private fun onCloseClicked(data: Person) {
        viewModel.deletePerson(data)
    }

    private fun addScrollListener(){
        binding.recyclerView.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val layoutManager =
                        recyclerView.layoutManager
                                as LinearLayoutManager

                    val totalItemCount =
                        layoutManager.itemCount

                    val lastVisibleItem =
                        layoutManager.findLastCompletelyVisibleItemPosition()

                    if ( lastVisibleItem >= totalItemCount - 3) {
                        viewModel.fetchMorePerson()
                    }
                }
            }
        )
    }

    fun setData(){
        lifecycleScope.launch {
            viewModel.data.collect { data ->
                    adapter.submitList(data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}