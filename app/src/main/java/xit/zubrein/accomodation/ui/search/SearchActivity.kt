package xit.zubrein.accomodation.ui.search

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import xit.zubrein.accomodation.R
import xit.zubrein.accomodation.Utils.Navigator
import xit.zubrein.accomodation.Utils.toast
import xit.zubrein.accomodation.base.BaseActivity
import xit.zubrein.accomodation.databinding.ActivitySearchBinding
import xit.zubrein.accomodation.model.ModelApartmentList
import xit.zubrein.accomodation.ui.appartment.ApartmentListActivity

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    var property_type : String? = "apartment"
    var location : String? = null
    var min_price : String? = null
    var max_price : String? = null
    var bed : Int? = 1
    var bath : Int? = 1

    override fun getView() = R.layout.activity_search

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        
        val locationData = intent.getBundleExtra("locationB")

        binding.back.setOnClickListener {
            finish()
        }

        binding.apply {

            if(locationData != null){
                tvLocation.text = locationData.getString("location")
                location = locationData.getString("location")
            }
            searchLocation.setOnClickListener {

                Navigator.sharedInstance.navigate(this@SearchActivity,LocationSearchActivity::class.java)

            }

            ptApartment.setOnClickListener {
                property_type = "apartment"
                ptApartment.setBackgroundResource(R.drawable.text_bg)
                ptSublet.setBackgroundResource(R.drawable.text_bg_white)
                ptOffice.setBackgroundResource(R.drawable.text_bg_white)
                ptDuplex.setBackgroundResource(R.drawable.text_bg_white)
            }

            ptSublet.setOnClickListener {
                property_type = "sublet"
                ptApartment.setBackgroundResource(R.drawable.text_bg_white)
                ptSublet.setBackgroundResource(R.drawable.text_bg)
                ptOffice.setBackgroundResource(R.drawable.text_bg_white)
                ptDuplex.setBackgroundResource(R.drawable.text_bg_white)
            }

            ptOffice.setOnClickListener {
                property_type = "office space"
                ptApartment.setBackgroundResource(R.drawable.text_bg_white)
                ptSublet.setBackgroundResource(R.drawable.text_bg_white)
                ptOffice.setBackgroundResource(R.drawable.text_bg)
                ptDuplex.setBackgroundResource(R.drawable.text_bg_white)
            }

            ptDuplex.setOnClickListener {
                property_type = "duplex"
                ptApartment.setBackgroundResource(R.drawable.text_bg_white)
                ptSublet.setBackgroundResource(R.drawable.text_bg_white)
                ptDuplex.setBackgroundResource(R.drawable.text_bg)
                ptOffice.setBackgroundResource(R.drawable.text_bg_white)
            }

            bed1.setOnClickListener {
                select_bedroom(1)
            }
            bed2.setOnClickListener {
                select_bedroom(2)
            }
            bed3.setOnClickListener {
                select_bedroom(3)
            }
            bed4.setOnClickListener {
                select_bedroom(4)
            }

            bath1.setOnClickListener {
                select_bath(1)
            }
            bath2.setOnClickListener {
                select_bath(2)
            }
            bath3.setOnClickListener {
                select_bath(3)
            }
            bath4.setOnClickListener {
                select_bath(4)
            }

            search.setOnClickListener {
                min_price = tvMinPrice.text.toString()
                max_price = tvMaxPrice.text.toString()

//                location = "Dampara,Chattogram"

                if(location.isNullOrEmpty()){
                    toast("Please select a location")
                } else if(min_price.isNullOrEmpty() or max_price.isNullOrEmpty()){
                    toast("Please enter minimum and maximum price range")
                }else{
                    loadingBar.showDialog()
                    searchViewModel.getApartments(location!!,min_price!!,max_price!!,property_type!!,bed.toString(),bath.toString()).observe(this@SearchActivity, {
                        if(it.isNotEmpty()){
                            loadingBar.cancelDialog()
                            val b = Bundle()
                            var model = ModelApartmentList(it)
                            b.putParcelable("model",model as Parcelable)
                            Navigator.sharedInstance.navigateWithBundle(
                                this@SearchActivity,ApartmentListActivity::class.java,"data",b
                            )
                        }else{
                            loadingBar.cancelDialog()
                            toast("No Data found")
                        }
                    })
                }
            }
        }
    }

    fun select_bath(value:Int){

        bath = value

        binding.apply {
            if(value == 1){
                setBg(bath1)
                setBgWhite(bath2)
                setBgWhite(bath3)
                setBgWhite(bath4)
            } else if(value == 2){
                setBg(bath2)
                setBgWhite(bath1)
                setBgWhite(bath3)
                setBgWhite(bath4)
            }else if(value == 3){
                setBg(bath3)
                setBgWhite(bath1)
                setBgWhite(bath2)
                setBgWhite(bath4)
            }else if(value == 4){
                setBg(bath4)
                setBgWhite(bath1)
                setBgWhite(bath3)
                setBgWhite(bath2)
            }
        }


    }
    fun select_bedroom(value:Int){

        bed = value

        binding.apply {
            if(value == 1){
                setBg(bed1)
                setBgWhite(bed2)
                setBgWhite(bed3)
                setBgWhite(bed4)
            } else if(value == 2){
                setBg(bed2)
                setBgWhite(bed1)
                setBgWhite(bed3)
                setBgWhite(bed4)
            }else if(value == 3){
                setBg(bed3)
                setBgWhite(bed1)
                setBgWhite(bed2)
                setBgWhite(bed4)
            }else if(value == 4){
                setBg(bed4)
                setBgWhite(bed1)
                setBgWhite(bed3)
                setBgWhite(bed2)
            }
        }


    }

    fun setBg(view:TextView){
        view.setBackgroundResource(R.drawable.text_bg)
    }

    fun setBgWhite(view:TextView){
        view.setBackgroundResource(R.drawable.text_bg_white)
    }

}