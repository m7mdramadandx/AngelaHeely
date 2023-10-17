package com.example.angelaheely.domain.usecases

import com.example.angelaheely.domain.Repo
import com.example.angelaheely.domain.model.User
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repo: Repo
) {

    suspend fun execute() =
        repo.getCurrentUser()

}