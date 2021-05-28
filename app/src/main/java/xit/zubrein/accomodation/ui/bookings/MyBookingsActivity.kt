package xit.zubrein.accomodation.ui.bookings

import android.content.Intent
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.adapter.MyBookingsAdapter
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityMyBookingsBinding

@AndroidEntryPoint
class MyBookingsActivity : BaseActivity<ActivityMyBookingsBinding>() {

    override fun getView() = (R.layout.activity_my_bookings)

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        binding.back.setOnClickListener {
            finish()
        }

        binding.progressBar.visibility = View.VISIBLE
        bookingViewModel.my_bookings(pref.getString(PrefKeys.ID)).observe(this,{
            binding.progressBar.visibility = View.GONE
            val adapter by lazy { MyBookingsAdapter(this) }
            binding.recyclerView.adapter = adapter
            adapter.addItems(it)
        })

    }

}