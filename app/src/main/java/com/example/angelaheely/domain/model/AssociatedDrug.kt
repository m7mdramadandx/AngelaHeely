package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class AssociatedDrug(
    @SerializedName("dose") val dose: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("strength") val strength: String?
) : Parcelable