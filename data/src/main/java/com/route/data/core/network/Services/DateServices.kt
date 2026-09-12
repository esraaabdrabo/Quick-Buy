package com.route.data.core.network.Services

import java.time.Instant

interface DateServices {
    fun getCurrentDate(): Instant
    fun convertDateFromTimeStamp(timeStamp: Long): Instant
}