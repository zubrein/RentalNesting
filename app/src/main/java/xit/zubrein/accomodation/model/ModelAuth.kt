package xit.zubrein.accomodation.model

data class ModelAuth(

    var status_code:Int,
    var otp_token:String,
    var user_status:String,
    var user:User?,
    var data:User?

){
    data class User (
        val user_role : String,
        val name : String,
        val email : String,
        val phone : String,
        val nid : String,
        val image : String,
        val status : Int,
        val updated_at : String,
        val created_at : String,
        val id : Int
    )
}
