package xit.zubrein.accomodation.ui.auth

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.UploadRequestBody
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityRegistrationBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@AndroidEntryPoint
class RegistrationActivity : BaseActivity<ActivityRegistrationBinding>(){

    override fun getView() = R.layout.activity_registration

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        binding.sendOtp.setOnClickListener {
            var msisdn = binding.contactNo.text.toString()
            if(msisdn.length == 11 && msisdn.startsWith("016") || msisdn.startsWith("018")){
                loadingBar.showDialog()
                authViewModel.send_otp(msisdn).observe(this,{
                    if(it.status_code == 200){
                        loadingBar.cancelDialog()
                        val b = Bundle()
                        b.putString("msisdn",msisdn)
                        b.putString("otp_token",it.otp_token)
                        Navigator.sharedInstance.navigateWithBundle(this, OtpActivity::class.java,"data",b)
                        finish()
                    }else{
                        toast("OTP send failed, Please try again later")
                        loadingBar.cancelDialog()
                    }
                })
            }else{
                toast("Enter a valid number")
            }
        }

    }

}