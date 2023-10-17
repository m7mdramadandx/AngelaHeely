package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Problem(
    @SerializedName("Asthma") val asthma: List<Asthma>?,
    @SerializedName("Diabetes") val diabetes: List<Diabete>?
) : Parcelable