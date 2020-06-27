package cn.yy.demo.corou


import com.google.gson.annotations.SerializedName

data class WanBundle<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("errorCode")
    val errorCode: Int = 0,
    @SerializedName("errorMsg")
    val errorMsg: String = ""
)