package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class DrugsResponse(
    @SerializedName("drugs") val drugs: List<Drug>?
) : Parcelable