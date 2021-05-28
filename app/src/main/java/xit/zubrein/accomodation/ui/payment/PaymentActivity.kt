package xit.zubrein.accomodation.ui.payment

import android.content.Intent
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityPaymentBinding
import xit.zubrein.accomodation.ui.home.HomeActivity

@AndroidEntryPoint
class PaymentActivity : BaseActivity<ActivityPaymentBinding>() {

    override fun getView() = (R.layout.activity_payment)

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        val data = intent.getBundleExtra("data")
        val appartment_id = data?.getString("appartment_id")

        binding.close.setOnClickListener {
            finish()
        }

        binding.next.setOnClickListener {

            val stMsisdn = binding.msisdn.text.toString()

            if (stMsisdn.startsWith("018")
                || stMsisdn.startsWith("017")
                || stMsisdn.startsWith("016")
                || stMsisdn.startsWith("015")
                || stMsisdn.startsWith("013")
                || stMsisdn.startsWith("014")
                || stMsisdn.startsWith("019")
            ) {
                if (stMsisdn.length == 11) {
                    binding.msisdnMenu.setVisibility(View.GONE)
                    binding.pinMenu.setVisibility(View.VISIBLE)
                } else {
                    toast("Please enter a valid mobile number")
                }
            } else {
                toast("Please enter a valid mobile number")
            }
        }

        binding.confirm.setOnClickListener {
            if (binding.pin.text.toString().equals("12345")) {
                loadingBar.showDialog()
                bookingViewModel.booking(pref.getString(PrefKeys.ID), appartment_id!!)
                    .observe(this, {
                        if (it.status_code == 200) {
                            loadingBar.cancelDialog()
                            toast("Apartment booked successfully")
                            Navigator.sharedInstance.navigateClear(this, HomeActivity::class.java)
                        } else if (it.status_code == 402) {
                            loadingBar.cancelDialog()
                            toast("You have already sent a request")
                            Navigator.sharedInstance.navigateClear(this, HomeActivity::class.java)
                        }
                    })
            } else {
                toast("Enter a valid pin")
            }
        }
    }

}