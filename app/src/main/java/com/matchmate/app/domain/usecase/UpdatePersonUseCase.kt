package com.matchmate.app.domain.usecase

import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import javax.inject.Inject

class UpdatePersonUseCase @Inject constructor(val mainRepository: MainRepository) {
    suspend operator fun invoke(person: Person) {
//         Implementation for updating a person's information
        mainRepository.updatePerson(person)
    }
}