package com.example.moneyconvert.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.moneyconvert.R
import com.example.moneyconvert.databinding.FragmentOutputBinding
import com.example.moneyconvert.di.App
import com.example.moneyconvert.presentation.viewmodel.OutputViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BASE_CODE_KEY = "BASE_CODE_KEY"
private const val TARGET_CODE_KEY = "TARGET_CODE_KEY"
private const val AMOUNT_OF_CURRENCY_KEY = "AMOUNT_OF_CURRENCY_KEY"

class OutputFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: OutputViewModel.OutPutViewModelFactory
    private lateinit var viewModel: OutputViewModel

    private var _binding: FragmentOutputBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        _binding = FragmentOutputBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel()
        setupTextToListenChanges()
    }

    private fun setupTextToListenChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resultAmountStateFlow.collect {
                    binding.targetValueText.text = getString(
                        R.string.conversion_result,
                        it?.conversionResult.toString(),
                        it?.targetCode
                    )
                }
            }
        }
        setupProgressBarToListenChanges()
    }

    private fun setupProgressBarToListenChanges() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoadingStateFlow.collect { isLoading ->
                    if (isLoading) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.targetValueText.visibility = View.INVISIBLE
                    } else {
                        binding.progressBar.visibility = View.INVISIBLE
                        binding.targetValueText.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun getViewModel(): OutputViewModel {
        val baseCode = requireArguments().getString(BASE_CODE_KEY)
        val targetCode = requireArguments().getString(TARGET_CODE_KEY)
        val amountOfCurrency = requireArguments().getDouble(AMOUNT_OF_CURRENCY_KEY)
        viewModelFactory.baseCode = baseCode ?: "USD"
        viewModelFactory.targetCode = targetCode ?: "EUR"
        viewModelFactory.amountOfCurrency = amountOfCurrency
        return ViewModelProvider(this, viewModelFactory)[OutputViewModel::class]
    }

    companion object {
        fun newInstance(
            baseCode: String,
            targetCode: String,
            amountOfCurrency: Double
        ): OutputFragment {
            return OutputFragment().apply {
                arguments = Bundle().also {
                    it.putString(BASE_CODE_KEY, baseCode)
                    it.putString(TARGET_CODE_KEY, targetCode)
                    it.putDouble(AMOUNT_OF_CURRENCY_KEY, amountOfCurrency)
                }
            }
        }
    }
}