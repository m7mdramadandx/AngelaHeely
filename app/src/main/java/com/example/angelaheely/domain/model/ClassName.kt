package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ClassName(
    @SerializedName("associatedDrug") val associatedDrug: List<AssociatedDrug>?,
    @SerializedName("associatedDrug#2") val associatedDrug2: List<AssociatedDrug>?
) : Parcelable