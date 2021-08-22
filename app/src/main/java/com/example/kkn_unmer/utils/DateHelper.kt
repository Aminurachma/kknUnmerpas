package com.example.kkn_unmer.utils

import java.text.SimpleDateFormat

object DateHelper {

    private val SECOND_MILLIS = 1000
    private val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private val DAY_MILLIS = 24 * HOUR_MILLIS


    fun getTimeAgo(time: Long): String? {
        var time = time
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000
        }

        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }

        // TODO: localize
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "Baru saja"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "Satu menit yang lalu"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} menit yang lalu"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "Satu jam yang lalu"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} jam yang lalu"
        } else if (diff < 48 * HOUR_MILLIS) {
            "Kemarin"
        } else {
            "${diff / DAY_MILLIS} hari yang lalu"
        }
    }

    fun getTimeTelat(time: Long): String? {
        var time = time
        if (time < 1000000000000L) {
            // if timestamp given in seconds, convert to millis
            time *= 1000
        }

        val x = "08:00:00"
//        val jamDatang = getDateTimeFromString(x).minusHours(1)
//        val inputDateFormat = SimpleDateFormat(Constants.CURRENT_TIME_FORMAT).parse(jamDatang.toString(
//            timeHoursFormat))
        val now = x.toLong()
        if (time > now || time <= 0) {
            return null
        }

        // TODO: localize
        val diff = now - time
        return if (diff < MINUTE_MILLIS) {
            "Baru saja"
        } else if (diff < 2 * MINUTE_MILLIS) {
            "Satu menit yang lalu"
        } else if (diff < 50 * MINUTE_MILLIS) {
            "${diff / MINUTE_MILLIS} menit yang lalu"
        } else if (diff < 90 * MINUTE_MILLIS) {
            "Satu jam yang lalu"
        } else if (diff < 24 * HOUR_MILLIS) {
            "${diff / HOUR_MILLIS} jam yang lalu"
        } else if (diff < 48 * HOUR_MILLIS) {
            "Kemarin"
        } else {
            "${diff / DAY_MILLIS} hari yang lalu"
        }
    }

}