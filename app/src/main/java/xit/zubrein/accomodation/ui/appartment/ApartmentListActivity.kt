package xit.zubrein.accomodation.ui.appartment

import android.content.Intent
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.adapter.AppartmentListAdapter
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityAppartmentListBinding
import xit.zubrein.accomodation.model.ModelApartmentList

@AndroidEntryPoint
class ApartmentListActivity : BaseActivity<ActivityAppartmentListBinding>(){

    private lateinit var model : ModelApartmentList


    override fun getView() = R.layout.activity_appartment_list

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        val data = intent.getBundleExtra("data")
        model = data?.getParcelable("model")!!

        val adapter by lazy { AppartmentListAdapter(this) }
        binding.recyclerView.adapter = adapter
        adapter.addItems(model.list)

        binding.filter.setOnClickListener {
            finish()
        }

        binding.back.setOnClickListener {
            finish()
        }


    }

}