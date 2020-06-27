package cn.yy.demo.corou

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Weather(
    @SerializedName("weatherinfo")
    val weatherinfo: Weatherinfo = Weatherinfo()
) : Parcelable