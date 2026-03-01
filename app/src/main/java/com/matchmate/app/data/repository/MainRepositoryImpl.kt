package com.matchmate.app.data.repository

import android.util.Log
import com.matchmate.app.core.utils.JsonUtil.toJson
import com.matchmate.app.core.utils.Result
import com.matchmate.app.data.local.dao.MainDao
import com.matchmate.app.data.local.entity.User
import com.matchmate.app.data.mapper.toPersonEntity
import com.matchmate.app.data.mapper.toPersonEntityList
import com.matchmate.app.data.mapper.toPersonList
import com.matchmate.app.data.service.MainService
import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val service: MainService, private val database: MainDao
) : MainRepository {
    companion object {
        val TAG = MainRepositoryImpl::class.java.simpleName
    }

    override suspend fun fetchPersons(): Result<List<Person>> {
        try {
            val users = fetchDataFromApi()
            users?.let { user ->
                val entities = user.toPersonEntityList()
                database.clearPersons()
                database.insertAllPersons(entities)
                return Result.Success(entities.toPersonList())
            }
        } catch (e: Exception) {
            return Result.Error(message = e.message ?: "Unknown error", error = e)
        }
        return Result.Error(message = "No data found", error = Exception("No data found"))
    }

    override suspend fun fetchMorePersons(): Result<List<Person>> {
        try {
            val users = fetchDataFromApi()
            users?.let { user ->
                val entities = user.toPersonEntityList()
                database.insertAllPersons(entities)
                return Result.Success(entities.toPersonList())
            }
        } catch (e: Exception) {
            return Result.Error(message = e.message ?: "Unknown error", error = e)
        }
        return Result.Error(message = "No data found", error = Exception("No data found"))
    }

    override fun getDataFromDatabase(): Flow<List<Person>> {
        return database.fetchPersonsEntities().map { it.toPersonList() }
    }

    override suspend fun updatePerson(person: Person) {
        database.updatePerson(person.toPersonEntity())
    }

    override fun deletePerson(person: Person) {
        database.deletePerson(person.toPersonEntity())
    }

    private suspend fun fetchDataFromApi(): List<User>? {
        repeat(3) { attempt ->
            try {
                val response = service.fetchData(4)
                if (response.isSuccessful) {
                    Log.d(TAG, "fetchDataFromApi: ${response.body()?.results.toJson()}")
                    return response.body()?.results
                }
            } catch (e: IOException) {
                delay((attempt + 1) * 1000L)
            } catch (e: HttpException) {
                throw e
            }
        }
        return null
    }
}