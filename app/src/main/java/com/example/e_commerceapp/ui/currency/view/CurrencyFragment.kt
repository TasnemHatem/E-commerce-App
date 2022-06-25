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
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.utils.ConnectionLiveData
import com.example.e_commerceapp.utils.Constants
import javax.inject.Inject


@AndroidEntryPoint
class CurrencyFragment : BaseFragment<FragmentCurrencyBinding>(FragmentCurrencyBinding::inflate) {

    @Inject
    lateinit var appSharedPreference: AppSharedPreference

    private var currencyResponse: ConversionRates? = null
    private var currencyData: MutableList<Currency> = mutableListOf<Currency>()
    lateinit var currencyAdapter: CurrencyAdapter
    val viewmodel: CurrencyVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenerToNetwork()
        currencyAdapter =
            CurrencyAdapter(requireContext(), currencyData, appSharedPreference.getStringValue(
                Constants.SHARED_CURRENCY_CODE, "USD"), ::changeCurrency)
        binding.currencyRecycleviewId.adapter = currencyAdapter


        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        binding.currencyRecycleviewId.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.currencyRecycleviewId.getContext(),
            VERTICAL
        )
        binding.currencyRecycleviewId.addItemDecoration(dividerItemDecoration)


        viewmodel.currencyResponse.observeInFragment(viewLifecycleOwner) {
            this.currencyResponse = it.conversionRates
            var count = 0
            currencyData.clear()
            for (prop in ConversionRates::class.memberProperties) {
                currencyData.add(Currency(prop.name.uppercase(),
                    prop.get(currencyResponse!!) as Double))
                count++
            }
            Log.i("TAG", "onViewCreated: currency count = $count")
            binding.progressBarCurrencyId.visibility = View.GONE
            currencyAdapter.data = currencyData
            currencyAdapter.notifyDataSetChanged()
        }
        binding.progressBarCurrencyId.visibility = View.VISIBLE
        binding.btnBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    fun changeCurrency(currencyCode: String, currencyValue: Double) {
        appSharedPreference.setValue(Constants.SHARED_CURRENCY_CODE, currencyCode)
        appSharedPreference.setValue(Constants.SHARED_CURRENCY_VALUE, currencyValue)
        currencyAdapter.currencyCode = currencyCode
        currencyAdapter.notifyDataSetChanged()
    }

    private fun listenerToNetwork() {
        ConnectionLiveData(requireContext()).observe(viewLifecycleOwner) {
            if (it) {
                binding.currencyRecycleviewId.visibility = View.VISIBLE
                binding.progressBarCurrencyId.visibility = View.VISIBLE
                binding.imageView.visibility = View.GONE
                viewmodel.requestCurrency()
            } else {
                binding.currencyRecycleviewId.visibility = View.GONE
                binding.progressBarCurrencyId.visibility = View.GONE
                binding.imageView.visibility = View.VISIBLE
            }
        }
    }

}

