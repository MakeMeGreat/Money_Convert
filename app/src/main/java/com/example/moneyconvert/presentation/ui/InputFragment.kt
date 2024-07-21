package com.example.moneyconvert.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
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
        setupBaseCadeSpinner()
        setupTargetCodeSpinner()
        setupExchangeButton()
    }

    private fun setupBaseCadeSpinner() {
        val baseSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.base_code_array,
            android.R.layout.simple_spinner_item
        )
        baseSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        /*        binding.baseCodeSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val selectedItem = parent.getItemAtPosition(position).toString()
                            Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {}
                    }*/
        binding.baseCodeSpinner.adapter = baseSpinnerAdapter

    }

    private fun setupTargetCodeSpinner() {
        val targetSpinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.target_code_array,
            android.R.layout.simple_spinner_item
        )
        targetSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        /*        binding.targetCodeSpinner.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            val selectedItem = parent.getItemAtPosition(position).toString()
                            Toast.makeText(requireContext(), "Selected: $selectedItem", Toast.LENGTH_SHORT)
                                .show()
                        }

                        override fun onNothingSelected(parent: AdapterView<*>) {}
                    }*/
        binding.targetCodeSpinner.adapter = targetSpinnerAdapter
    }

    private fun setupExchangeButton() {
        binding.exchangeButton.setOnClickListener {
            val baseCode = binding.baseCodeSpinner.selectedItem
            val targetCode = binding.targetCodeSpinner.selectedItem

            Toast.makeText(requireContext(), "$baseCode  $targetCode", LENGTH_SHORT).show()
        }
    }
}