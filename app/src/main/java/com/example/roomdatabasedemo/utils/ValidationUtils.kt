package com.example.roomdatabasedemo.utils

import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.roomdatabasedemo.ApplicationClass
import com.example.roomdatabasedemo.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

object ValidationUtils {
    const val REGEX_PHONE = "^[6789]\\d{9}$"
    const val REGEX_EMAIL =
        "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-zA-Z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"



    fun containsText(editText: EditText): Boolean {
        val text = editText.text.toString().trim { it <= ' ' }

        if (text.isEmpty()) {
            return false
        }
        return true
    }

    // return true if the input field is valid, based on the parameter passed
    @RequiresApi(Build.VERSION_CODES.M)
    private fun isValid(layout: TextInputLayout, stringData: String, regex: String?, errMsg: String?, required: Boolean): Boolean {
        val text = stringData.trim { it <= ' ' }

        if (required && !Pattern.matches(regex, text)) {
            layout.error = errMsg
            layout.boxBackgroundColor =ApplicationClass.getApp().getColor(R.color.error_box)
            return false
        }
        return true
    }

    // call this method when you need to check phone number validation
    @RequiresApi(Build.VERSION_CODES.M)
    fun validatePhoneNumber(layout: TextInputLayout, stringData: String, errMsg: String?, required: Boolean): Boolean {
        return isValid(layout, stringData, REGEX_PHONE, errMsg, required)
    }

    // call this method when you need to check email id validation
    @RequiresApi(Build.VERSION_CODES.M)
    fun validateEmailId(layout: TextInputLayout, stringData: String, errMsg: String?, required: Boolean): Boolean {
        return isValid(layout, stringData, REGEX_EMAIL, errMsg, required)
    }

    fun clearTextInputEditTextAndLayoutErrorMessage(layout: TextInputLayout, textInputEditText: TextInputEditText) {
        textInputEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            @RequiresApi(Build.VERSION_CODES.M)
            override fun afterTextChanged(editable: Editable) {
                layout.error = null
                layout.boxBackgroundColor =ApplicationClass.getApp().getColor(R.color.error_box)
            }
        })
    }

}