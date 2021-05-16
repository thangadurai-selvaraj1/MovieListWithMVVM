package com.thangadurai.saveotask.data.network

import com.thangadurai.saveotask.data.model.photo.BasePhotoResponse
import retrofit2.Response
import retrofit2.http.GET

@JvmSuppressWildcards
interface ApiHelper {

    @GET(ApiUtils.PHOTOS)
    suspend fun getPhotosId(): Response<BasePhotoResponse>

}