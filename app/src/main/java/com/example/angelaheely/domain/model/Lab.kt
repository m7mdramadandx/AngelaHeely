package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Lab(
    @SerializedName("missing_field") val missingField: String?
) : Parcelable