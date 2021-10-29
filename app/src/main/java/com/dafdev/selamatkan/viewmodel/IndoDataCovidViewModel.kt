package com.dafdev.selamatkan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dafdev.selamatkan.data.domain.usecase.HealthUseCase

class IndoDataCovidViewModel(private val covid: HealthUseCase) : ViewModel() {

    fun dataCovidIndo() = covid.getDataCovidIndonesia().asLiveData()
}