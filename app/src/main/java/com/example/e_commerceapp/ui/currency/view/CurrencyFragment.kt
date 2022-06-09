package com.example.e_commerceapp.ui.currency.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.databinding.FragmentCurrencyBinding
import com.example.e_commerceapp.ui.currency.model.ConversionRates
import com.example.e_commerceapp.ui.currency.model.Currency
import com.example.e_commerceapp.ui.currency.model.CurrencyResponse
import com.example.e_commerceapp.ui.currency.viewmodel.CurrencyVM
import dagger.hilt.android.AndroidEntryPoint
import kotlin.reflect.full.memberProperties
import androidx.recyclerview.widget.DividerItemDecoration




@AndroidEntryPoint
class CurrencyFragment : BaseFragment<FragmentCurrencyBinding>(FragmentCurrencyBinding::inflate){


    private var currencyResponse: ConversionRates? = null
    private var currencyData: MutableList<Currency> = mutableListOf<Currency>()
    lateinit var currencyAdapter: CurrencyAdapter
    val viewmodel: CurrencyVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyAdapter = CurrencyAdapter(requireContext(), currencyData)
        binding.currencyRecycleviewId.adapter = currencyAdapter


        var layoutManager: RecyclerView.LayoutManager =  LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        binding.currencyRecycleviewId.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.currencyRecycleviewId.getContext(),
            VERTICAL
        )
        binding.currencyRecycleviewId.addItemDecoration(dividerItemDecoration)

        viewmodel.currencyResponse.observeInFragment(viewLifecycleOwner){
            this.currencyResponse = it.conversionRates

            Log.i("EMYTAG", "onViewCreated: Ya Reflect")

            for (prop in ConversionRates::class.memberProperties) {
                currencyData.add(Currency(prop.name.capitalize(), prop.get(currencyResponse!!) as Double))
                //println("${prop.name} = ${prop.get(currencyResponse!!)}")
            }
            currencyAdapter.data = currencyData
            currencyAdapter.notifyDataSetChanged()

            Log.i("EMYTAG", "onViewCreated: Bye bye")
            //Log.i("EMYTAG", "onViewCreated: Ya Currency $it")
        }
        viewmodel.requestCurrency()
    }


}