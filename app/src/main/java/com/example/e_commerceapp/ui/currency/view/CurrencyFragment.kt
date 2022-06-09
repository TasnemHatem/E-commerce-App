package com.example.e_commerceapp.ui.currency.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCurrencyBinding
import com.example.e_commerceapp.ui.currency.model.ConversionRates
import com.example.e_commerceapp.ui.currency.model.CurrencyResponse
import com.example.e_commerceapp.ui.currency.viewmodel.CurrencyVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyFragment : BaseFragment<FragmentCurrencyBinding>(FragmentCurrencyBinding::inflate){


    private var currencyData: ConversionRates? = null
    lateinit var currencyAdapter: CurrencyAdapter
    val viewmodel: CurrencyVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyAdapter = CurrencyAdapter(requireContext(), currencyData)
        binding.currencyRecycleviewId.adapter = currencyAdapter

        var layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        binding.currencyRecycleviewId.layoutManager = layoutManager

        viewmodel.currencyResponse.observeInFragment(viewLifecycleOwner){
            this.currencyData = it.conversionRates
            Log.i("EMYTAG", "onViewCreated: Ya Currency $it")
        }
        viewmodel.requestCurrency()
    }


}