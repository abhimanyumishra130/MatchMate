package com.matchmate.app.data.repository

import com.matchmate.app.core.utils.Result
import com.matchmate.app.data.local.dao.MainDao
import com.matchmate.app.data.local.entity.User
import com.matchmate.app.data.mapper.toPersonEntityList
import com.matchmate.app.data.mapper.toPersonList
import com.matchmate.app.data.service.MainService
import com.matchmate.app.domain.model.Person
import com.matchmate.app.domain.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(val service: MainService, val database: MainDao): MainRepository {
    override suspend fun fetchData(): Flow<Result<List<Person>>> =
        flow{
            emit(Result.Loading)
            val cachedData = database.fetchData()
            if (cachedData.isNotEmpty()){
                emit(Result.Success(cachedData.toPersonList()))
            }

            val fetchAllData = fetchDataFromApi()
            fetchAllData?.let{
                val entities = it.toPersonEntityList()
                database.clearPersons()
                database.insertAllPersons(entities)
                emit(Result.Success(entities.toPersonList()))
            }
        }.catch { e->
            val cached = database.fetchData()

            if (cached.isNotEmpty()) {
                emit(Result.Success(cached.toPersonList()))
            } else {
                emit(
                    Result.Error(
                        message = e.message
                            ?: "Unknown error",
                        error = e
                    )
                )
            }
        }.flowOn(Dispatchers.IO)

    suspend fun fetchDataFromApi(): List<User>?{
        repeat(3){ attempt ->
            try{
                val response = service.fetchData(20)
                if(response.isSuccessful){
                    return response.body()?.results
                }
            }catch (e: IOException){
                delay(
                    (attempt+1) * 1000L
                )
            }
            catch (e: HttpException){
                throw e
            }
        }
        return null
    }


}