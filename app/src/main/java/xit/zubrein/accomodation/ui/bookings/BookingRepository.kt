package xit.zubrein.accomodation.ui.bookings

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.model.ModelAuth
import xit.zubrein.accomodation.network.ApiService
import javax.inject.Inject

class BookingRepository @Inject constructor(private val apiService: ApiService) {

    fun booking(user_id:String,apartment_id:String) : Flow<ModelAuth> = flow {
       val response = apiService.booking(user_id,apartment_id)
       emit(response)
    }.flowOn(Dispatchers.IO)

    fun my_bookings(user_id: String) : Flow<List<ModelApartment>> = flow {
        val response = apiService.my_booking(user_id)
        emit(response)
    }.flowOn(Dispatchers.IO)

}