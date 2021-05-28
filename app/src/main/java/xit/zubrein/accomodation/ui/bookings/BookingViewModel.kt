package xit.zubrein.accomodation.ui.bookings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.model.ModelAuth
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(private val bookingRepository: BookingRepository) : ViewModel() {

    private val TAG = "BookingViewModel"

    fun booking(user_id:String,apartment_id:String) : LiveData<ModelAuth>{
        val response = bookingRepository.booking(user_id,apartment_id).
                catch {
                    e ->
                    Log.d(TAG, "booking: ${e.message}")
                }.asLiveData()

        return response

    }


    fun my_bookings(user_id: String) : LiveData<List<ModelApartment>> {
        val response = bookingRepository.my_bookings(user_id).
                catch {
                    e ->
                    Log.d(TAG, "my_bookings: ${e.message}")
                }.asLiveData()

        return  response
    }

}