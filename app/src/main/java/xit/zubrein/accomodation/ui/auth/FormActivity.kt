package xit.zubrein.accomodation.ui.auth

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.UploadRequestBody
import xit.zubrein.accomodation.Utils.ValidationUtils
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityFormBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FormActivity : BaseActivity<ActivityFormBinding>(){

    private var selectedImageUri: Uri? = null

    override fun getView() = R.layout.activity_form

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        val data = intent.getBundleExtra("data")
        val msisdn = data?.getString("msisdn")

        binding.done.setOnClickListener {
            val name = binding.name.text.toString()
            val email = binding.email.text.toString()
            val nid = binding.nid.text.toString()
            val password = binding.password.text.toString()
            val con_pass = binding.conPassword.text.toString()

            if (name.isEmpty()) {
                toast("Please enter your name")
            } else if (email.isEmpty() || !ValidationUtils.isValidEmail(email)) {
                toast("Please enter a valid email")
            } else if (nid.isEmpty()) {
                toast("Please enter a valid nid number")
            } else if (password.isEmpty()) {
                toast("Please enter a valid password")
            } else if (con_pass.isEmpty()) {
                toast("Please confirm your password")
            } else if (!con_pass.equals(password)) {
                toast("Password not matched")
            } else {
                authViewModel.registration(
                    name, email, msisdn!!, nid, "image", password
                ).observe(this, {
                    if (it.status_code == 200) {
                        loadingBar.cancelDialog()
                        pref.putString(PrefKeys.NAME, it.user?.name!!)
                        pref.putString(PrefKeys.EMAIL, it.user?.email!!)
                        pref.putString(PrefKeys.IMAGE, it.user?.image!!)
                        pref.putString(PrefKeys.MSISDN, it.user?.phone!!)
                        pref.putString(PrefKeys.NID, it.user?.nid!!)
                        pref.putString(PrefKeys.ID, it.user?.id.toString())
                        pref.putBoolean(PrefKeys.LOGIN_STATUS, true)
                        finish()
                    }
                })
            }

        }


    }

}