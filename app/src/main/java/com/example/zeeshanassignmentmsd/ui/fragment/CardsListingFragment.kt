package com.example.zeeshanassignmentmsd.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zeeshanassignmentmsd.adapter.CardsAdapter
import com.example.zeeshanassignmentmsd.data.model.DeckCard
import com.example.zeeshanassignmentmsd.databinding.CardsListFragmentBinding
import com.example.zeeshanassignmentmsd.ui.CardItemClickListener
import com.example.zeeshanassignmentmsd.ui.activity.CardActivity
import com.example.zeeshanassignmentmsd.utils.Constants.Companion.CARD_PARAM
import com.example.zeeshanassignmentmsd.utils.Status
import com.example.zeeshanassignmentmsd.viewmodel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardsListingFragment : Fragment(), CardItemClickListener {
    private lateinit var binding: CardsListFragmentBinding
    private lateinit var cardsAdapter: CardsAdapter
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CardsListFragmentBinding.inflate(layoutInflater)
        setupUI()
        setupObserver()
        return binding.root
    }

    private fun setupUI() {
        setUpCurrencyRecyclerView()
    }

    private fun setUpCurrencyRecyclerView() {
        binding.currencyRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        cardsAdapter = CardsAdapter(requireContext(), arrayListOf(), this)
        binding.currencyRecyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.currencyRecyclerView.context,
                (binding.currencyRecyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.currencyRecyclerView.adapter = cardsAdapter

    }


    private fun setupObserver() {
        mainViewModel.deckOfCards.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    CoroutineScope(Dispatchers.Main).launch() {
                        binding.progressBar.visibility = View.GONE
                        binding.currencyRecyclerView.visibility = View.VISIBLE
                        val decOfCardsObject = it.data?.cards
                        Log.e("cards", "abc=" + Gson().toJson(decOfCardsObject))
                        if (decOfCardsObject != null) {
                            renderCardsList(decOfCardsObject as MutableList<DeckCard>)
                        }
                    }
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.currencyRecyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun renderCardsList(cardsList: MutableList<DeckCard>) {
        cardsAdapter.updateData(cardsList)
        cardsAdapter.notifyDataSetChanged()

    }

    override fun onCLick(deckCard: DeckCard) {
        startActivity(
            Intent(requireContext(), CardActivity::class.java).putExtra(CARD_PARAM, deckCard)
        )
    }

}
