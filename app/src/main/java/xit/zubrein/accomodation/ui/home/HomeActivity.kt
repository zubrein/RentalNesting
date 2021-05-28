package xit.zubrein.accomodation.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityHomeBinding
import xit.zubrein.accomodation.listener.DemoListener
import xit.zubrein.accomodation.model.ModelDemo
import xit.zubrein.accomodation.ui.auth.FormActivity
import xit.zubrein.accomodation.ui.auth.LoginActivity
import xit.zubrein.accomodation.ui.bookings.MyBookingsActivity
import xit.zubrein.accomodation.ui.search.SearchActivity

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun getView() = R.layout.activity_home

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        binding.apply {
            search.setOnClickListener {
                Navigator.sharedInstance.navigate(this@HomeActivity,SearchActivity::class.java)
            }
        }

        footer()

        binding.logout.setOnClickListener {
            if(pref.getBoolean(PrefKeys.LOGIN_STATUS)){
                pref.clearPreference()
                footer()
            }else{
                Navigator.sharedInstance.navigate(this,LoginActivity::class.java)
            }

        }

        binding.myBookings.setOnClickListener {
            Navigator.sharedInstance.navigate(this,MyBookingsActivity::class.java)
        }

        //This is test comment


    }


    override fun onResume() {
        super.onResume()
        footer()
    }

    fun footer(){
        if(pref.getBoolean(PrefKeys.LOGIN_STATUS)){
            binding.myBookings.visibility = View.VISIBLE
            binding.loginText.text = "LOGOUT"
        }else{
            binding.myBookings.visibility = View.INVISIBLE
            binding.loginText.text = "LOGIN"
        }
    }
}