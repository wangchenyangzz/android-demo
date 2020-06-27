package cn.yy.demo.corou
import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
@Keep
data class Weatherinfo(
    @SerializedName("city")
    val city: String = "",
    @SerializedName("cityid")
    val cityid: String = "",
    @SerializedName("temp1")
    val temp1: String = "",
    @SerializedName("temp2")
    val temp2: String = "",
    @SerializedName("weather")
    val weather: String = "",
    @SerializedName("img1")
    val img1: String = "",
    @SerializedName("img2")
    val img2: String = "",
    @SerializedName("ptime")
    val ptime: String = ""
) : Parcelable