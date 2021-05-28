package xit.zubrein.accomodation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.model.ModelLocation
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val TAG = "SearchViewModel"

    fun getLocation(location:String) : LiveData<List<ModelLocation>>{

        val response : LiveData<List<ModelLocation>> = searchRepository.getLocation(location).catch {
            e ->
            Log.d(TAG, "getLocation: ${e.message}")
        }.asLiveData()

        return response

    }

    fun getApartments(
        location:String,
        price_min:String,
        price_max:String,
        category:String,
        bed:String,
        bath:String
    ) : LiveData<List<ModelApartment>> {
        val response : LiveData<List<ModelApartment>> = searchRepository.getApartments(
            location,
            price_min,
            price_max,
            category,
            bed,
            bath
        ).catch {
            e ->
            Log.d(TAG, "getApartments: ${e.message}")
        }.asLiveData()

        return response
    }

}