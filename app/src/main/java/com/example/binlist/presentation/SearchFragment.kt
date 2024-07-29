package com.example.binlist.presentation

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.binlist.app.App
import com.example.binlist.databinding.FragmentSearchBinding
import com.example.binlist.domain.models.Card
import com.example.binlist.utils.Factory
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel> {
        Factory {
            App.appComponent.searchComponent().viewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            val bin = binding.editTextNumberSigned.text.toString()
            viewModel.getCard(bin)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.observeUi().collect { state ->
                    state.card?.let { setData(it) }
                    state.error?.let {
                        Toast.makeText(
                            requireContext(),
                            getString(it),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData(card: Card) {
        binding.countryName.text = card.country.name
        binding.coordinatyName.text = "(latitude: ${card.country.latitude}, longitude: ${card.country.longitude})"
        binding.typeName.text = card.scheme
        binding.nameAndCityName.text = "${card.bank.name}, ${card.bank.city}"
        binding.urlName.text = card.bank.url
        binding.phoneName.text = card.bank.phone
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}