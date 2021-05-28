package xit.zubrein.accomodation.listener

import androidx.lifecycle.LiveData
import xit.zubrein.accomodation.model.ModelDemo

interface DemoListener {

    fun onStarted()

    fun onSuccess(response: LiveData<ModelDemo>)

    fun onFailure(message : String)

}