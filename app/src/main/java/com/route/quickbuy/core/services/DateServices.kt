package com.route.quickbuy.core.services

import com.route.data.core.network.Services.DateServices
import java.time.Instant
import javax.inject.Inject

class DateServicesImpl @Inject constructor() : DateServices {
    override fun getCurrentDate(): Instant {
        return Instant.now()
    }

    override fun convertDateFromTimeStamp(timeStamp: Long): Instant {
        return Instant.ofEpochSecond(timeStamp)
    }
}
