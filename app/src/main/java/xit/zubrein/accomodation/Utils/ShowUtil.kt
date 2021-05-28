package xit.zubrein.accomodation.Utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import xit.zubrein.accomodation.R

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@BindingAdapter("loadImage")
fun loadImage(view:ImageView,url:String){
    Glide.with(view.context)
        .load(url)
        .placeholder(R.mipmap.ic_launcher) // change this
        .into(view)

}