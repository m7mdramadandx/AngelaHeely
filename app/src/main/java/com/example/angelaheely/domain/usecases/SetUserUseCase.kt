package com.example.angelaheely.domain.usecases

import com.example.angelaheely.domain.Repo
import com.example.angelaheely.domain.model.User
import javax.inject.Inject

class SetUserUseCase @Inject constructor(
    private val repo: Repo
) {

    fun execute(user: User) =
        repo.setUser(user)

}