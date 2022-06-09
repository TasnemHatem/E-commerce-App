package com.example.e_commerceapp.ui.address.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.e_commerceapp.R
import com.example.e_commerceapp.base.LiveDataUtils.observeInFragment
import com.example.e_commerceapp.base.ui.BaseFragment
import com.example.e_commerceapp.base.utils.safeNavigation
import com.example.e_commerceapp.databinding.FragmentAddressBinding
import com.example.e_commerceapp.ui.address.model.Address
import com.example.e_commerceapp.ui.address.viewmodel.AddressVM
import com.example.e_commerceapp.ui.checkout.model.PostOrderBody
import com.example.e_commerceapp.utils.POST_ORDER_BODY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressFragment : BaseFragment<FragmentAddressBinding>(FragmentAddressBinding::inflate),
    OnAddressClickListener {

    private var postOrderBody: PostOrderBody? = null
    private lateinit var addresslist: List<Address>
    lateinit var addressAdapter: AddressAdapter

    val viewmodel: AddressVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postOrderBody = arguments?.getParcelable<PostOrderBody>(POST_ORDER_BODY)
        binding.addAdressBtnId.setOnClickListener {
            navController.navigate(R.id.action_addressFragment_to_newAddressFragment)
        }

        addresslist = listOf()
        addressAdapter = AddressAdapter(requireContext(), addresslist, this)
        binding.addressRecycleViewId.adapter = addressAdapter


        binding.addressRecycleViewId.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

    }

    override fun afterOnCreateView() {
        super.afterOnCreateView()

        viewmodel.error.observeInFragment(viewLifecycleOwner) {
            //TODO Add logic depending on Exception Type
        }

        viewmodel.addresslist.observeInFragment(viewLifecycleOwner) {
            Log.i("TAG", "afterOnCreateView: Ya address data ${it}")
            addresslist = it
            addressAdapter.data = addresslist
            addressAdapter.notifyDataSetChanged()
        }
        viewmodel.requestAddresses()
    }

    override fun clickDelete(userId: Long, addressId: Long) {
        viewmodel.deleteAddress(userId, addressId)
    }

    override fun changeDefault(userId: Long, addressId: Address) {
        if (postOrderBody != null) {
            navController.safeNavigation(R.id.addressFragment,
                R.id.action_addressFragment_to_paymentFragment,
                Bundle().apply {
                    putParcelable(POST_ORDER_BODY, postOrderBody?.order.apply {
                        this?.shippingAddress = addressId
                    })
                })
        } else {
            viewmodel.changeDefaultAddress(userId, addressId.id!!)
        }
    }
}