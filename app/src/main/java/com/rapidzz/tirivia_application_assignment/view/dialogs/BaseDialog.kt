package com.rapidzz.tirivia_application_assignment.view.dialogs

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.rapidzz.tirivia_application_assignment.R


abstract class BaseDialog(): DialogFragment() {


    @SuppressLint("ResourceAsColor")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        val builder = AlertDialog.Builder(requireContext(), R.style.DialogStyle)
        builder.setCancelable(false)
        val inflater = requireActivity().layoutInflater
        val dialog = inflater.inflate(getLayoutId(), null)
        initViews(dialog)
        builder.setView(dialog)
        return builder.create()
    }

    abstract fun initViews(view: View)

    abstract fun getLayoutId():Int

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        val windowParams = window?.attributes
        windowParams?.dimAmount = 0.8f
        window?.attributes = windowParams
    }




}