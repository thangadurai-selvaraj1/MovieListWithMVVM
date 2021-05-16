package com.thangadurai.saveotask.repo

import com.thangadurai.saveotask.data.model.photo.BasePhotoResponse
import com.thangadurai.saveotask.data.network.ApiHelper
import com.thangadurai.saveotask.data.network.Resource
import com.thangadurai.saveotask.data.network.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AuthRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {

    suspend fun getPhotosId(): Flow<Resource<BasePhotoResponse>> {
        return flow {
            val result = apiHelper.getPhotosId().body()
            emit(Resource(Status.SUCCESS, result, null))
        }.catch {
            emit(Resource(Status.ERROR, null, it.message))
        }
    }

}