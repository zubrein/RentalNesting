package xit.zubrein.accomodation.adapter

import android.content.Context
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.base.BaseRecyclerviewAdapter
import xit.zubrein.accomodation.databinding.AppartementItemsBinding
import xit.zubrein.accomodation.databinding.LocationItemBinding
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.model.ModelDemo
import xit.zubrein.accomodation.model.ModelLocation
import xit.zubrein.accomodation.ui.appartment.ApartmentDetailsActivity
import xit.zubrein.accomodation.ui.search.SearchActivity

class AppartmentListAdapter(private val context:Context) : BaseRecyclerviewAdapter<ModelApartment,AppartementItemsBinding>(){
    override fun getLayout() = R.layout.appartement_items

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<AppartementItemsBinding>,
        position: Int
    ) {
        holder.binding.apply {
            model = items[position]
            parentLayout.setOnClickListener {
                val b = bundle
                b.putParcelable("model",items[position])
                Navigator.sharedInstance.navigateWithBundle(context,ApartmentDetailsActivity::class.java,"data",b)
            }
        }
    }


}