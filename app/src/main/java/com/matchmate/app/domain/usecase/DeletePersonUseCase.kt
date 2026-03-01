package com.matchmate.app.domain.usecase

import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import javax.inject.Inject

class DeletePersonUseCase @Inject constructor(val mainRepository: MainRepository) {
    suspend operator fun invoke(person: Person) {
        mainRepository.deletePerson(person)
    }
}