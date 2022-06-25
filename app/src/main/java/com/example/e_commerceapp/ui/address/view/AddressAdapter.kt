package com.example.e_commerceapp.ui.address.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentAddressBinding
import com.example.e_commerceapp.databinding.FragmentAddressRowBinding
import com.example.e_commerceapp.ui.address.model.Address


class AddressAdapter(var context: Context, var data: List<Address>, val listener: OnAddressClickListener) : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = FragmentAddressRowBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val v: View = inflater.inflate(R.layout.fragment_address_row, parent, false)
        val vh: ViewHolder = ViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.addresNameId.text = data[position].firstName + " " + data[position].lastName
        holder.binding.addressPhoneId.text = data[position].phone
        holder.binding.addressDetailsId.text = data[position].address1 + " " + data[position].address2
        holder.binding.addressGeneralId.text = data[position].city + " " + data[position].country
        holder.binding.defaultRadioBtnId.isChecked = data[position].default
        holder.binding.deleteAddressId.setOnClickListener {
            //listener.clickDelete(data[position].userId!!, data[position].id!!)
            delete(data[position].userId!!, data[position].id!!)
        }
        holder.binding.defaultRadioBtnId.setOnClickListener{
            listener.changeDefault(data[position].userId!!, data[position])
        }
    }

    override fun getItemCount(): Int {
        if(data != null)
            return data.size
        else return 0

    }

    fun delete(userId: Long, addressId: Long){
        var buildr = AlertDialog.Builder(context)
        buildr.setTitle("Confirm Remove")
        buildr.setMessage("Are you sure you want to remove this address?")
        buildr.setPositiveButton("Yes", DialogInterface.OnClickListener{
                dialog, id ->
            Log.i("TAG", "onBindViewHolder: Remove it")
            listener.clickDelete(userId, addressId)
            dialog.cancel()
        })
        buildr.setNegativeButton("Cancel", DialogInterface.OnClickListener{
                dialog, id ->
            Log.i("TAG", "onBindViewHolder: Don't remove it")
            dialog.cancel()
        })
        var alert = buildr.create()
        alert.show()
    }


}