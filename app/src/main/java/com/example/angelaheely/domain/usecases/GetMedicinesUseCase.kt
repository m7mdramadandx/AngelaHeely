package com.example.angelaheely.domain.usecases

import com.example.angelaheely.domain.Repo
import javax.inject.Inject

class GetMedicinesUseCase @Inject constructor(
    private val repo: Repo
) {

    suspend fun execute() =
        repo.getMedicines()

}