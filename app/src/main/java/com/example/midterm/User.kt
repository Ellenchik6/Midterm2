package com.example.midterm
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("results")
    var results: List<ResultResponse>,

    @SerializedName("nationality")
    var nat: String?,

    @SerializedName("email")
    var email: String?,

)

data class ResultResponse(
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val author: String?,
    @SerializedName("first")
    val title: String?,
    @SerializedName("last")
    val description: String?,
)
