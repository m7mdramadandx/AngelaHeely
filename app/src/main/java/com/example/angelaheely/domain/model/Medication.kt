package com.example.angelaheely.domain.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Medication(
    @SerializedName("medicationsClasses") val medicationsClasses: List<MedicationsClasse>?
) : Parcelable