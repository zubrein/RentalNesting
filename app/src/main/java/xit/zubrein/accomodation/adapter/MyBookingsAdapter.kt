package xit.zubrein.accomodation.adapter

import android.content.Context
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.base.BaseRecyclerviewAdapter
import xit.zubrein.accomodation.databinding.MyBookingsItemsBinding
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.ui.appartment.ApartmentDetailsActivity

class MyBookingsAdapter(private val context:Context) : BaseRecyclerviewAdapter<ModelApartment,MyBookingsItemsBinding>(){

    override fun getLayout() = R.layout.my_bookings_items

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<MyBookingsItemsBinding>,
        position: Int
    ) {
        holder.binding.apply {
            model = items[position]
            if(items[position].rent_status == 1){
                status.setTextColor(context.resources.getColor(R.color.teal_700))
            }
            parentLayout.setOnClickListener {
                val b = bundle
                b.putParcelable("model",items[position])
                b.putString("from","my_bookings")
                Navigator.sharedInstance.navigateWithBundle(context,ApartmentDetailsActivity::class.java,"data",b)
            }
        }
    }


}