package xit.zubrein.accomodation.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import xit.zubrein.accomodation.Utils.LoadingBar
import xit.zubrein.accomodation.Utils.pref.PreferenceManager
import xit.zubrein.accomodation.ui.bookings.BookingViewModel
import xit.zubrein.accomodation.ui.auth.AuthViewModel
import xit.zubrein.accomodation.ui.search.SearchViewModel
import javax.inject.Inject


abstract class BaseActivity <T : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: T

    val searchViewModel : SearchViewModel by viewModels()
    val authViewModel : AuthViewModel by viewModels()
    val bookingViewModel : BookingViewModel by viewModels()

    @Inject
    lateinit var loadingBar: LoadingBar

    @Inject
    lateinit var pref: PreferenceManager

    protected val TAG: String  by lazy {
        this.javaClass.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,getView())
        onViewReady(savedInstanceState, intent)

    }

    abstract fun getView() : Int
    abstract fun onViewReady(savedInstanceState: Bundle?, intent: Intent)


}