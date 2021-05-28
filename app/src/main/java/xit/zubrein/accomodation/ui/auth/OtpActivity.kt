package xit.zubrein.accomodation.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import dagger.hilt.android.AndroidEntryPoint
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityOtpBinding

@AndroidEntryPoint
class OtpActivity : BaseActivity<ActivityOtpBinding>() {

    var otp: String = ""
    var msisdn: String = ""
    var otp_token: String = ""

    override fun getView() = R.layout.activity_otp

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        val data = intent.getBundleExtra("data")
        msisdn = data?.getString("msisdn")!!
        otp_token = data?.getString("otp_token")!!

        binding.pinView.apply {
            setHideLineWhenFilled(false);
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    otp = s.toString()
                }

                override fun afterTextChanged(s: Editable) {}
            })
        }

        binding.next.setOnClickListener {
            if(otp.length < 4){
                toast("Please enter a valid otp")
            }else{
                loadingBar.showDialog()
                authViewModel.submit_otp(otp,otp_token).observe(this,{
                    if(it.status_code == 200){
                        loadingBar.cancelDialog()
                        if(it.user_status.equals("new")){
                            val b = Bundle()
                            b.putString("msisdn",msisdn)
                            Navigator.sharedInstance.navigateWithBundle(this,FormActivity::class.java,"data",b)
                            finish()
                        }
                    }
                })
            }
        }

    }


}