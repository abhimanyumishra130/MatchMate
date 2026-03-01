package com.matchmate.app.domain.usecase

import com.matchmate.app.domain.repository.MainRepository
import javax.inject.Inject

class GetDataUseCase @Inject constructor(val mainRepository: MainRepository) {
    operator fun invoke() = mainRepository.getDataFromDatabase()
}