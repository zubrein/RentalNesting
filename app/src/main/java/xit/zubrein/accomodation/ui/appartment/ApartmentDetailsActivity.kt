package xit.zubrein.accomodation.ui.appartment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.HTTP
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.pref.PrefKeys
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivityApartmentDetailsBinding
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.network.data.ApiConstants
import xit.zubrein.accomodation.ui.auth.LoginActivity
import xit.zubrein.accomodation.ui.payment.PaymentActivity


@AndroidEntryPoint
class ApartmentDetailsActivity : BaseActivity<ActivityApartmentDetailsBinding>() {

    private lateinit var data: ModelApartment

    override fun getView() = R.layout.activity_apartment_details

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {

        val model = intent.getBundleExtra("data")
        data = model?.getParcelable("model")!!
        val from = model.getString("from")
        binding.model = data

        if (from.equals("my_bookings")) {
            binding.statusLay.visibility = View.VISIBLE
            binding.bookNow.visibility = View.INVISIBLE
            if(data.rent_status == 1){
                binding.downloadAgreement.visibility = View.VISIBLE
            }
            if (data.rent_status == 0) {
                binding.status.text = "PENDING"
            } else {
                binding.status.text = "ACCEPTED"
                binding.status.setTextColor(resources.getColor(R.color.teal_700))
            }
        }

        binding.downloadAgreement.setOnClickListener {
            openBrowser("${ApiConstants.BASE_URL}download_pdf/${pref.getString(PrefKeys.ID)}")
        }

        binding.back.setOnClickListener {
            finish()
        }

        binding.bookNow.setOnClickListener {
            if (!pref.getBoolean(PrefKeys.LOGIN_STATUS)) {
                Navigator.sharedInstance.navigate(this, LoginActivity::class.java)
            } else {

                val b = Bundle()
                b.putString("appartment_id", data.id.toString())
                Navigator.sharedInstance.navigateWithBundle(
                    this,
                    PaymentActivity::class.java,
                    "data",
                    b
                )

            }
        }

    }

    fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(
            Intent.createChooser(
                intent,
                "Choose browser"
            )
        )
    }

}