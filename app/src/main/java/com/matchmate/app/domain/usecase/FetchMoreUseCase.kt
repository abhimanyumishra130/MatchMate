package com.matchmate.app.domain.usecase

import com.matchmate.app.core.utils.Result
import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import javax.inject.Inject

class FetchMoreUseCase @Inject constructor(val mainRepository: MainRepository) {
        suspend operator fun invoke(): Result<List<Person>> = mainRepository.fetchMorePersons()
}