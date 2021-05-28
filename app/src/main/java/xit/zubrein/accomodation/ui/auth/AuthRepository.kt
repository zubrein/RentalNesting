package xit.zubrein.accomodation.ui.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part
import retrofit2.http.PartMap
import xit.zubrein.accomodation.Utils.UploadRequestBody
import xit.zubrein.accomodation.model.ModelAuth
import xit.zubrein.accomodation.model.ModelLocation
import xit.zubrein.accomodation.network.ApiService
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiService: ApiService) {

    fun send_otp(msisdn:String) : Flow<ModelAuth> = flow {
        val response = apiService.send_otp(msisdn)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun login(msisdn:String,password: String) : Flow<ModelAuth> = flow {
        val response = apiService.login(msisdn,password)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun submit_otp(otp:String,otp_token:String) : Flow<ModelAuth> = flow {
        val response = apiService.submit_otp(otp,otp_token)
        emit(response)
    }.flowOn(Dispatchers.IO)

    fun registarion(
        name:String,
        email:String,
        msisdn:String,
        nid:String,
        user_image:String,
        password:String
    ) : Flow<ModelAuth> = flow {
        val response = apiService.registration(name,email,msisdn,nid,user_image,password)
        emit(response)
    }.flowOn(Dispatchers.IO)


}