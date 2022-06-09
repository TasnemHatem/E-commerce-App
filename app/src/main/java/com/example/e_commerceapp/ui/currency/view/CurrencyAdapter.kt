package com.example.e_commerceapp.ui.currency.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentCurrencyRowBinding
import com.example.e_commerceapp.ui.currency.model.ConversionRates

class CurrencyAdapter(var context: Context, var data: ConversionRates?) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
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

    }

    override fun getItemCount(): Int {
        if(data != null)
            return 10
        else
            return 0
    }

}