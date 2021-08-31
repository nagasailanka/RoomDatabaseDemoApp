package com.example.roomdatabasedemo.utils

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

object AlertDialogUtils {

    private fun showDefaultDialog(context: Context, title: String, message: String) {
        val builder = AlertDialog.Builder(context)

        builder.apply {

            setTitle(title)
            setMessage(message)
            setPositiveButton("OK") { _, _ ->
                toast("clicked Positive button", context)
            }
            setNegativeButton("Negative") { _, _ ->
                toast("clicked negative button", context)
            }
            setNeutralButton("Neutral") { _, _ ->
                toast("clicked neutral button", context)
            }
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    private fun toast(string: String, context: Context) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
}