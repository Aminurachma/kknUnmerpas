package com.example.kkn_unmer.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.datetime.datePicker
import com.example.kkn_unmer.BuildConfig
import com.example.kkn_unmer.utils.Constants.COUNTRY_CODE_ID
import com.example.kkn_unmer.utils.Constants.LANGUAGE_ID
import com.github.dhaval2404.imagepicker.ImagePicker
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

fun View.toVisible() {
    visibility = View.VISIBLE
}

fun View.toGone() {
    visibility = View.GONE
}

fun logDebug(message: String) {
    if (BuildConfig.DEBUG)
        Log.d(Constants.TAG_DEBUG, message)
}

/**
 * Log Error Message Locally and Send to Error Reporting Server
 */
fun logError( message: String, throwable : Throwable? = null) {
    if (BuildConfig.DEBUG) {
//        DEBUG VERSION
//        LOG LOCALLY
        Log.e(Constants.TAG_ERROR, message)
    }
}

val cal = Calendar.getInstance()

val dateTimeFormat = "yyyy/MM/dd"
val dateTimeHoursFormat = "yyyy-MM-dd HH:mm:ss"
val timeHoursFormat = "HH:mm:ss"
val fixedDateFormat = "dd MMMM yyyy"

val timeStampFormat = "yyyy-MM-dd"

fun dialogDate(context: Context, dateResult: (dateResult: String) -> Unit){
    val dateNow = DateTime.now()
    MaterialDialog(context).show {
        datePicker(maxDate = dateNow.toCalendar(Locale(LANGUAGE_ID, COUNTRY_CODE_ID))) { dialog, date ->
            val dateTime = DateTime(date)
            dateResult(dateTime.toString(dateTimeFormat))
        }
    }
}

fun dialogStartDate(context: Context, dateResult: (dateResult: String) -> Unit){
    val dateNow = DateTime.now()
    MaterialDialog(context).show {
        datePicker(minDate = dateNow.toCalendar(Locale(LANGUAGE_ID, COUNTRY_CODE_ID))) { dialog, date ->
            val dateTime = DateTime(date)
            dateResult(dateTime.toString(dateTimeFormat))
        }
    }
}

fun dialogEndDate(context: Context, minDate: Calendar, dateResult: (dateResult: String) -> Unit){
    MaterialDialog(context).show {
        datePicker(minDate = minDate) { dialog, date ->
            val dateTime = DateTime(date)
            dateResult(dateTime.toString(dateTimeFormat))
        }
    }
}

fun getTimeFromMillis(milis: Long): String {
    return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(milis),
        TimeUnit.MILLISECONDS.toMinutes(milis) - TimeUnit.HOURS.toMinutes(
            TimeUnit.MILLISECONDS.toHours(milis)),
        TimeUnit.MILLISECONDS.toSeconds(milis) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(milis)))
}

fun priceFormatter(price: Int): String{
    return NumberFormat.getNumberInstance(Locale(LANGUAGE_ID, COUNTRY_CODE_ID)).format(price)
}

fun getDateFromString(date: String): DateTime {
    return DateTime.parse(date, DateTimeFormat.forPattern(dateTimeFormat))
}

fun getTimeFromString(times: String): DateTime {
    return DateTime.parse(times, DateTimeFormat.forPattern(timeHoursFormat))
}

fun getDateTimeFromString(date: String): DateTime {
    return DateTime.parse(date, DateTimeFormat.forPattern(dateTimeHoursFormat))
}

fun getDayFromDate(date: DateTime): String {
    return date.dayOfWeek().getAsText(Locale(LANGUAGE_ID, COUNTRY_CODE_ID))
}

private val VALID_EMAIL_ADDRESS_REGEX: Pattern =
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

fun isValidEmail(emailStr: String?): Boolean {
    val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr)
    return matcher.find().not()
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.checkPermissionForLocation(context: Context, REQUEST_PERMISSION_LOCATION : Int): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            // Show the permission request
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSION_LOCATION)
            false
        }
    } else {
        true
    }
}

fun Activity.checkPermissionStorage(context: Context, REQUEST_PERMISSION_STORAGE : Int): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        if (context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            // Show the permission request
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_PERMISSION_STORAGE)
            false
        }
    } else {
        true
    }
}

fun Fragment.imagePicker(launcher: ActivityResultLauncher<Intent>) {
    ImagePicker.with(this)
        .crop()
        .compress(1024)
        .createIntent { intent ->
            launcher.launch(intent)
        }
}

fun Activity.imagePicker(launcher: ActivityResultLauncher<Intent>) {
    ImagePicker.with(this)
        .crop()
        .compress(1024)
        .createIntent { intent ->
            launcher.launch(intent)
        }
}

fun autoNextEditText(editText: AppCompatEditText, editText2: AppCompatEditText){
    editText.addTextChangedListener(object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (editText.length() == 1)
            {
                editText2.requestFocus()
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // TODO Auto-generated method stub
        }

        override fun afterTextChanged(s: Editable) {
            // TODO Auto-generated method stub
        }

    })
}