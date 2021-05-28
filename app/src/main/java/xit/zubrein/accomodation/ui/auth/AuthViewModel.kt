package xit.zubrein.accomodation.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import xit.zubrein.accomodation.model.ModelAuth
import xit.zubrein.accomodation.model.ModelLocation
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel(){

    private val TAG = "AuthViewModel"

    fun send_otp(msisdn:String) : LiveData<ModelAuth> {

        val response : LiveData<ModelAuth> = authRepository.send_otp(msisdn).catch {
                e ->
            Log.d(TAG, "send_otp: ${e.message}")
        }.asLiveData()

        return response

    }

    fun submit_otp(otp:String,otp_token:String) : LiveData<ModelAuth> {

        val response : LiveData<ModelAuth> = authRepository.submit_otp(otp,otp_token).catch {
                e ->
            Log.d(TAG, "submit_otp: ${e.message}")
        }.asLiveData()

        return response

    }
    fun login(msisdn:String,password:String) : LiveData<ModelAuth> {

        val response : LiveData<ModelAuth> = authRepository.login(msisdn,password).catch {
                e ->
            Log.d(TAG, "login: ${e.message}")
        }.asLiveData()

        return response

    }

    fun registration(
        name:String,
        email:String,
        msisdn:String,
        nid:String,
        user_image:String,
        password:String
    ) : LiveData<ModelAuth> {

        val response : LiveData<ModelAuth> = authRepository.registarion(
            name,email,msisdn,nid,user_image,password
        ).catch {
                e ->
            Log.d(TAG, "registration: ${e.message}")
        }.asLiveData()

        return response

    }

}