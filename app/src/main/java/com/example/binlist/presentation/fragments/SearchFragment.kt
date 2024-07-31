package com.example.binlist.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
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
import com.example.binlist.presentation.viewmodels.SearchViewModel
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

        binding.urlName.setOnClickListener {
            openUrl()
        }

        binding.phoneName.setOnClickListener {
            openPhone()
        }

        binding.coordinatyName.setOnClickListener {
            val list = binding.coordinatyName.text.toString().split(",")
            val latitude = list[0].filter { it.isDigit() }
            val longitude = list[1].filter { it.isDigit() }
            pinLocationMap(latitude, longitude)
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
        binding.coordinatyName.text =
            "(latitude: ${card.country.latitude}, longitude: ${card.country.longitude})"
        binding.typeName.text = card.scheme
        binding.nameAndCityName.text = "${card.bank.name}, ${card.bank.city}"
        binding.urlName.text = card.bank.url
        binding.phoneName.text = card.bank.phone
    }

    private fun openUrl() {
        val url = "http://${binding.urlName.text}"
        val urlIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        startActivity(urlIntent)
    }

    private fun openPhone() {
        val url = binding.phoneName.text.toString()
        val urlIntent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$url")
        }
        startActivity(urlIntent)
    }

    private fun pinLocationMap(latitude: String, longitude: String) {
        val gmmIntentUri = Uri.parse("google.streetview:cbll=$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}