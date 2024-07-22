package com.example.moneyconvert.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.moneyconvert.R
import com.example.moneyconvert.databinding.FragmentInputBinding
import com.example.moneyconvert.di.App

class InputFragment : Fragment() {

    private var _binding: FragmentInputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        _binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBaseCodeSpinner()
        setupTargetCodeSpinner()
        setupExchangeButton()
    }

    private fun setupBaseCodeSpinner() {
        val baseSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.base_code_array,
            android.R.layout.simple_spinner_item
        )
        baseSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.baseCodeSpinner.adapter = baseSpinnerAdapter
    }

    private fun setupTargetCodeSpinner() {
        val targetSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.target_code_array,
            android.R.layout.simple_spinner_item
        )
        targetSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.targetCodeSpinner.adapter = targetSpinnerAdapter
    }

    private fun setupExchangeButton() {
        binding.exchangeButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_view, OutputFragment.newInstance(
                        binding.baseCodeSpinner.selectedItem.toString(),
                        binding.targetCodeSpinner.selectedItem.toString(),
                        binding.amountOfCurrencyEditText.text.toString().ifEmpty { "0" }.toDouble()
                    )
                )
                .addToBackStack(null)
                .commit()
        }
    }
}