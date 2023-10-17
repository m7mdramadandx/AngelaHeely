package com.example.angelaheely.domain.usecases

import com.example.angelaheely.domain.Repo
import javax.inject.Inject

class DeleteCurrentUserUseCase @Inject constructor(
    private val repo: Repo
) {

    fun execute() =
        repo.deleteCurrentUser()

}