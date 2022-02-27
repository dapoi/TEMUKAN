package com.dafdev.selamatkan.utils

import com.dafdev.selamatkan.data.domain.model.*
import com.dafdev.selamatkan.data.source.local.model.CovidIndoEntity
import com.dafdev.selamatkan.data.source.local.model.ProvinceEntity
import com.dafdev.selamatkan.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {
    fun mapCovidResponseToEntity(dataResponse: IndoDataCovidResponse): CovidIndoEntity =
        CovidIndoEntity(
            0,
            dataResponse.positif,
            dataResponse.sembuh,
            dataResponse.meninggal
        )

    fun mapProvinceResponseToEntity(provinceResponse: List<ProvincesItem>): List<ProvinceEntity> {
        val listProv = ArrayList<ProvinceEntity>()
        provinceResponse.map {
            val provinceEntity = ProvinceEntity(
                it.name,
                it.id,
            )
            listProv.add(provinceEntity)
        }
        return listProv
    }

    fun mapArticlesToNews(input: List<Articles?>?): Flow<List<News>> {
        val listNews = ArrayList<News>()
        input?.map {
            val news = News(
                it?.author,
                it?.title,
                it?.publishedAt,
                it?.urlToImage,
                it?.description,
                it?.url,
                it?.content,
            )
            listNews.add(news)
        }
        return flowOf(listNews)
    }

    fun mapProvinceCovidResponseToProvince(provincesItem: List<ProvinceCovidResponse>): Flow<List<CovidProv>> {
        val listProv = ArrayList<CovidProv>()
        provincesItem.map {
            val prov = CovidProv(
                it.provinsi,
                it.meninggal,
                it.sembuh,
                it.kasus
            )
            listProv.add(prov)
        }
        return flowOf(listProv)
    }

    fun mapProvinceResponseToProvince(provincesItem: List<ProvincesItem>): Flow<List<Province>> {
        val listProv = ArrayList<Province>()
        provincesItem.map {
            val prov = Province(
                it.id,
                it.name
            )
            listProv.add(prov)
        }
        return flowOf(listProv)
    }

    fun mapCitiesResponseToCities(provincesItem: List<CitiesItem>): Flow<List<Cities>> {
        val listCities = ArrayList<Cities>()
        provincesItem.map {
            val prov = Cities(
                it.id,
                it.name
            )
            listCities.add(prov)
        }
        return flowOf(listCities)
    }

    fun mapHospitalCovidResponseToHospitalCovid(hospitalsCovidItem: List<HospitalsCovidItem>): Flow<List<HospitalCovid>> {
        val listHospital = ArrayList<HospitalCovid>()
        hospitalsCovidItem.map {
            val hospitalCovid = HospitalCovid(
                it.id,
                it.name,
                it.address,
                it.phone,
                it.info
            )
            listHospital.add(hospitalCovid)
        }
        return flowOf(listHospital)
    }

    fun mapHospitalNonCovidResponseToHospitalNonCovid(hospitalsNonCovidItem: List<HospitalsNonCovidItem>): Flow<List<HospitalNonCovid>> {
        val listHospital = ArrayList<HospitalNonCovid>()
        hospitalsNonCovidItem.map {
            val hospitalNonCovid = HospitalNonCovid(
                it.id,
                it.name,
                it.address,
                it.phone,
                it.available_beds?.get(0)?.info
            )
            listHospital.add(hospitalNonCovid)
        }
        return flowOf(listHospital)
    }

    fun mapHospitalDetailResponseToHospitalDetail(hospitalDetail: List<BedDetailItem>): Flow<List<DetailHospital>> {
        val listDetail = ArrayList<DetailHospital>()
        hospitalDetail.map {
            val detail = DetailHospital(
                it.stats?.title,
                it.stats?.bed_available,
                it.stats?.bed_empty,
                it.time
            )
            listDetail.add(detail)
        }
        return flowOf(listDetail)
    }

    fun mapLocationResponseToLocation(locationResponse: DataMapHospital): Flow<Location> =
        flowOf(
            Location(
                locationResponse.gmaps,
                locationResponse.address,
                locationResponse.name,
                locationResponse.id,
                locationResponse.lat,
                locationResponse.long
            )
        )
}