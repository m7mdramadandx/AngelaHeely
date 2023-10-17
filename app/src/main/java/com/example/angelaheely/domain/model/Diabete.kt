package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Diabete(
    @SerializedName("labs") val labs: List<Lab>?,
    @SerializedName("medications") val medications: List<Medication>?
) : Parcelable