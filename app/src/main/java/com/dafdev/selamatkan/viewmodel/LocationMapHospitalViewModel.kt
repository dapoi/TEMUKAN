package com.dafdev.selamatkan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dafdev.selamatkan.data.repository.HealthRepository
import com.dafdev.selamatkan.vo.Resource
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class LocationMapHospitalViewModel(private val location: HealthRepository) : ViewModel() {

    fun getLocationHospital(hospitalId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(null))
        try {
            val dataLocation = location.getLocationHospitalMap(hospitalId)
            emit(Resource.success(dataLocation))
            Timber.d(dataLocation.toString())
        } catch (e: Exception) {
            emit(Resource.error(null, e.message.toString()))
        }
    }
}