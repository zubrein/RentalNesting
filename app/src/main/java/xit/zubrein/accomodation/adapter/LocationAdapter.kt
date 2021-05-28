package xit.zubrein.accomodation.adapter

import android.content.Context
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.base.BaseRecyclerviewAdapter
import xit.zubrein.accomodation.databinding.AppartementItemsBinding
import xit.zubrein.accomodation.databinding.LocationItemBinding
import xit.zubrein.accomodation.model.ModelDemo
import xit.zubrein.accomodation.model.ModelLocation
import xit.zubrein.accomodation.ui.search.SearchActivity

class LocationAdapter(private val context:Context) : BaseRecyclerviewAdapter<ModelLocation,LocationItemBinding>(){
    override fun getLayout() = R.layout.location_item

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LocationItemBinding>,
        position: Int
    ) {
        holder.binding.apply {
            model = items[position]
            parentLayout.setOnClickListener {
                val b = bundle
                b.putString("location",items[position].location)
                Navigator.sharedInstance.navigateWithBundle(context,SearchActivity::class.java,"locationB",b)
            }
        }
    }


}