package com.example.e_commerceapp.ui.currency.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentCurrencyRowBinding
import com.example.e_commerceapp.local.AppSharedPreference
import com.example.e_commerceapp.ui.currency.model.ConversionRates
import com.example.e_commerceapp.ui.currency.model.Currency
import com.example.e_commerceapp.utils.Constants.SHARED_CURRENCY_CODE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Singleton

class CurrencyAdapter(var context: Context, var data: List<Currency>, var currencyCode: String, var operation: (currencyCode: String, currencyValue: Double) -> Unit) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = FragmentCurrencyRowBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_currency_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.currencyCodeId.text = data[position].currnecyName
        if(currencyCode == data[position].currnecyName){
            holder.binding.currecnyCheckId.setImageResource(R.drawable.ic_check)
        }else{
            holder.binding.currecnyCheckId.setImageResource(0)
        }
        holder.binding.layoutId.setOnClickListener {
            operation(data[position].currnecyName, data[position].currencyValue)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}