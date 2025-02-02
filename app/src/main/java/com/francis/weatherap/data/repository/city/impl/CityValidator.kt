package com.francis.weatherap.data.repository.city.impl

import com.francis.weatherap.data.repository.city.impl.model.CityModel
import com.francis.weatherap.data.repository.city.impl.model.CitySearchResponseModel
import com.francis.weatherap.data.repository.city.impl.model.MetadataModel

internal val CitySearchResponseModel.isValid: Boolean
    get() = metadata?.isValid == true && data?.isValid == true

internal val MetadataModel.isValid: Boolean
    get() = currentOffset != null && totalCount != null

internal val List<CityModel>.isValid: Boolean
    get() = all { city -> city.isValid }

internal val CityModel.isValid: Boolean
    get() = id != null &&
        name?.isNotEmpty() == true &&
        city?.isNotEmpty() == true &&
        region?.isNotEmpty() == true &&
        regionCode?.isNotEmpty() == true &&
        country?.isNotEmpty() == true &&
        countryCode?.isNotEmpty() == true &&
        latitude != null &&
        longitude != null
