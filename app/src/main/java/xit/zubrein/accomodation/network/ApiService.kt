package xit.zubrein.accomodation.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import xit.zubrein.accomodation.model.*
import xit.zubrein.accomodation.network.data.ApiConstants

interface ApiService {


    //Location search
    @FormUrlEncoded
    @POST(ApiConstants.LOCATION_SEARCH)
    suspend fun getLocation(
        @Field("location") location:String
    ) : List<ModelLocation>

    // Search Apartment
    @FormUrlEncoded
    @POST(ApiConstants.SEARCH_APARTMENT)
    suspend fun getApartemnts(
        @Field("location") location:String,
        @Field("price_min") price_min:String,
        @Field("price_max") price_max:String,
        @Field("category") category:String,
        @Field("bed") bed:String,
        @Field("bath") bath:String
    ) : List<ModelApartment>

    // Sent OTP
    @FormUrlEncoded
    @POST(ApiConstants.SEND_OTP)
    suspend fun send_otp(
        @Field("msisdn") msisdn:String
    ) : ModelAuth

    //Submit OTP
    @FormUrlEncoded
    @POST(ApiConstants.SUBMIT_OTP)
    suspend fun submit_otp(
        @Field("otp") otp:String,
        @Field("otp_token") otp_token:String
    ) : ModelAuth

    //Registration
    @FormUrlEncoded
    @POST(ApiConstants.REGISTRATION)
    suspend fun registration(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("msisdn") msisdn:String,
        @Field("nid") nid:String,
        @Field("user_image") user_image:String,
        @Field("password") password:String
    ) : ModelAuth

    //Login
    @FormUrlEncoded
    @POST(ApiConstants.LOGIN)
    suspend fun login(
        @Field("msisdn") user_id:String,
        @Field("password") apartment_id:String
    ) : ModelAuth

    //Booking apartment
    @FormUrlEncoded
    @POST(ApiConstants.BOOKING)
    suspend fun booking(
        @Field("user_id") user_id:String,
        @Field("apartment_id") apartment_id:String
    ) : ModelAuth


    //Booking apartment
    @FormUrlEncoded
    @POST(ApiConstants.MY_BOOKING)
    suspend fun my_booking(
        @Field("user_id") user_id:String
    ) : List<ModelApartment>


}