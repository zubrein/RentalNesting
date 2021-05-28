package xit.zubrein.accomodation.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityLoginBinding
import xit.zubrein.accomodation.ui.home.HomeActivity
import xit.zubrein.accomodation.ui.search.SearchActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getView() = R.layout.activity_login

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        binding.login.setOnClickListener {
            val msisdn = binding.contactNo.text.toString()
            val password = binding.password.text.toString()
            if (msisdn.length == 11 && msisdn.startsWith("016") || msisdn.startsWith("018")) {
                loadingBar.showDialog()
                authViewModel.login(msisdn, password).observe(this, {
                    if (it.status_code == 200) {
                        loadingBar.cancelDialog()
                        pref.putString(PrefKeys.NAME,it.data?.name!!)
                        pref.putString(PrefKeys.EMAIL,it.data?.email!!)
                        pref.putString(PrefKeys.IMAGE,it.data?.image!!)
                        pref.putString(PrefKeys.MSISDN,it.data?.phone!!)
                        pref.putString(PrefKeys.NID,it.data?.nid!!)
                        pref.putString(PrefKeys.ID,it.data?.id.toString())
                        pref.putBoolean(PrefKeys.LOGIN_STATUS,true)
                        Navigator.sharedInstance.navigateClear(this, HomeActivity::class.java)
                    } else if (it.status_code == 402) {
                        loadingBar.cancelDialog()
                        toast("Phone number and password not matched")
                    } else {
                        toast("User is not registered yet. Please register first.")
                        loadingBar.cancelDialog()
                    }
                })
            } else if (password.isEmpty()) {
                toast("Please enter your password")
            } else {
                toast("Please enter a valid phone number")
            }
        }

        binding.register.setOnClickListener {
            Navigator.sharedInstance.navigate(this, RegistrationActivity::class.java)
        }

    }

}