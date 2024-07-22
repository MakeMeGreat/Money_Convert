package com.example.moneyconvert.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.moneyconvert.domain.usecase.GetExchangeResponseUseCase
import com.example.moneyconvert.presentation.mapper.DomainToPresentationMapper
import com.example.moneyconvert.presentation.model.ExchangeResponseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

class OutputViewModel @Inject constructor(
    private val baseCode: String,
    private val targetCode: String,
    private val amountOfCurrency: Double,
    private val getExchangeResponseUseCase: GetExchangeResponseUseCase,
    private val mapper: DomainToPresentationMapper,
) : ViewModel() {

    private var _resultAmountMutableStateFlow = MutableStateFlow<ExchangeResponseModel?>(null)
    val resultAmountStateFlow: StateFlow<ExchangeResponseModel?> = _resultAmountMutableStateFlow

    private var _isLoadingStateFlow = MutableStateFlow(false)
    val isLoadingStateFlow: StateFlow<Boolean> = _isLoadingStateFlow

    init {
        getExchangeResponse()
    }

    private fun getExchangeResponse() {
        _isLoadingStateFlow.value = true
        viewModelScope.launch {
            try {
                val exchangeResponse =
                    getExchangeResponseUseCase(baseCode, targetCode, amountOfCurrency)
                _resultAmountMutableStateFlow.value = mapper.mapToPresentation(exchangeResponse)
                _isLoadingStateFlow.value = false
            } catch (e: Exception) {
                _isLoadingStateFlow.value = false
                Log.e("TAG", "${e.printStackTrace()}")
            }
        }
    }

    class OutPutViewModelFactory @Inject constructor(
        private val getExchangeResponseUseCase: GetExchangeResponseUseCase,
        private val mapper: DomainToPresentationMapper,
    ) : ViewModelProvider.Factory {

        lateinit var baseCode: String
        lateinit var targetCode: String
        var amountOfCurrency by Delegates.notNull<Double>()

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OutputViewModel(
                baseCode = baseCode,
                targetCode = targetCode,
                amountOfCurrency = amountOfCurrency,
                getExchangeResponseUseCase = getExchangeResponseUseCase,
                mapper = mapper
            ) as T
        }
    }
}