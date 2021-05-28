package xit.zubrein.accomodation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelMyBookings(
    var id : Int,
    var owner_id : String,
    var district : String,
    var zone : String,
    var address : String,
    var total_bed : String,
    var total_bath : String,
    var apartment_size : String,
    var apartment_description : String,
    var apartment_category : String,
    var flat_name : String,
    var floor_no : String,
    var apartment_rent : String,
    var active_status : String,
    var total_view : String,
    var commission_status : String,
    var holding_id : String,
    var created_at : String,
    var updated_at : String,
    var thumbnail_image : String,
    var owner : Owner,
    var rent_status : Int
) : Parcelable {
    @Parcelize
    data class Owner (
        val id : Int,
        val user_role : String,
        val name : String,
        val email : String,
        val phone : String,
        val nid : String,
        val image : String,
        val status : Int,
        val created_at : String,
        val updated_at : String
    ) :Parcelable
}


