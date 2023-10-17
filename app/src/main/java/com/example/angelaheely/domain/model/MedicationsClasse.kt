package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class MedicationsClasse(
    @SerializedName("className") val className: List<ClassName>?,
    @SerializedName("className2") val className2: List<ClassName>?
) : Parcelable