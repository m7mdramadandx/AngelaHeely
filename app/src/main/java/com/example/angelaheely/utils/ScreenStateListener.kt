package com.example.angelaheely.utils

import android.os.Parcelable

fun <T> isLoadingState(screenState: ScreenState<T>) =
    screenState.isLoading && screenState.items?.isEmpty() == true

fun <T> isFailureState(screenState: ScreenState<T>) =
    !screenState.isLoading && screenState.error.isNotEmpty()

fun <T> isEmptyState(screenState: ScreenState<T>) =
    !screenState.isLoading && screenState.items?.isEmpty() == true

fun isLoadingMoreState(screenState: ScreenState<out Parcelable?>) =
    screenState.isLoading && screenState.items.isNullOrEmpty().not()

fun <T> isSuccessDataState(screenState: ScreenState<T>) =
    !screenState.isLoading && screenState.item != null

