package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Response(
    @SerializedName("problems") val problems: List<Problem>?
) : Parcelable