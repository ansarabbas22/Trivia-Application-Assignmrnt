package com.rapidzz.tirivia_application_assignment.utils.application


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.rapidzz.tirivia_application_assignment.utils.factory.ViewModelFactory
import com.rapidzz.tirivia_application_assignment.view.dialogs.AlertMessageDialog

const val LEVEL_EASY = "easy"
const val LEVEL_MEDIUM = "medium"
const val LEVEL_HARD = "hard"

const val TYPE_MCQ = "multiple"
const val TYPE_TF = "boolean"

fun <T : ViewModel> AppCompatActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <T : ViewModel> FragmentActivity.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this, ViewModelFactory.getInstance(application)).get(viewModelClass)

fun <T : ViewModel> Fragment.obtainViewModel(viewModelClass: Class<T>) =
    ViewModelProviders.of(this,
        this.activity?.application?.let { ViewModelFactory.getInstance(it) }).get(
        viewModelClass
    )


fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun ImageView.loadImage(resourceID: Int) {
    Glide.with(context)
        .load(resourceID)
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun EditText.string(): String {
    return this.text.toString().trim()
}

fun Activity.openActivity(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
}

fun Activity.openActivityWithExist(clazz: Class<out Activity>) {
    startActivity(Intent(this, clazz))
    this.finish()
}


fun Activity.openActivityForResult(clazz: Class<out Activity>, requestCode: Int) {
    startActivityForResult(Intent(this, clazz), requestCode)
}


fun Fragment.showAlertDialog(msg: String) {
    var newMessage = msg
    if (newMessage.isEmpty()) {
        newMessage = "Unable to process your request \nPlease try again later !!"
    }
    AlertMessageDialog.newInstance(newMessage)
        .show(requireActivity().supportFragmentManager, AlertMessageDialog.TAG)
}

fun FragmentActivity.showAlertDialog(msg: String) {
    var newMessage = msg
    if (newMessage.isEmpty()) {
        newMessage = "Unable to process your request \nPlease try again later !!"
    }
    AlertMessageDialog.newInstance(newMessage).show(supportFragmentManager, AlertMessageDialog.TAG)
}

fun View.showSnackBar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}


fun Spinner.observe(onSelectedPosition: (Int) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parentView: AdapterView<*>,
            selectedItemView: View,
            position: Int,
            id: Long
        ) {

            onSelectedPosition.invoke(position)
        }

        override fun onNothingSelected(parentView: AdapterView<*>) {

        }

    }
}

fun isInternetConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}