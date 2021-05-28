package xit.zubrein.accomodation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelApartmentList(
    var list : List<ModelApartment>
) : Parcelable
