package xit.zubrein.accomodation.ui.search

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import xit.zubrein.accomodation.model.ModelApartment
import xit.zubrein.accomodation.model.ModelLocation
import xit.zubrein.accomodation.network.ApiService
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiService: ApiService) {

    fun getLocation(location:String) : Flow<List<ModelLocation>> = flow {
        val response = apiService.getLocation(location)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun getApartments(
        location:String,
        price_min:String,
        price_max:String,
        category:String,
        bed:String,
        bath:String
    ) : Flow<List<ModelApartment>> = flow {
        val response  = apiService.getApartemnts(
            location,
            price_min,
            price_max,
            category,
            bed,
            bath
        )
        emit(response)
    }.flowOn(Dispatchers.IO)

}