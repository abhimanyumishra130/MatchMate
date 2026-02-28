package com.matchmate.app.domain.usecase

import com.matchmate.app.core.utils.Response
import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPersonUseCase @Inject constructor(val mainRepository: MainRepository) {
    suspend operator fun invoke(): Flow<Response<List<Person>>> {
        return mainRepository.fetchData()
    }
}