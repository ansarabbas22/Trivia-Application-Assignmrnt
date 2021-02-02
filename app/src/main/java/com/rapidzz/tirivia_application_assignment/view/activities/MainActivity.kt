package com.rapidzz.tirivia_application_assignment.view.activities

import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.utils.application.gone
import com.rapidzz.tirivia_application_assignment.utils.application.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    fun setToolbarTitle(
        title: String,
        backHide: Boolean = false,
        hideExitButton: Boolean
    ) {
        tvToolbarName.text = title
        if (backHide) {
            ivBack.visible()
            ivExit.gone()
        } else if (hideExitButton) {
            ivExit.visible()
            ivBack.gone()
        } else {
            ivBack.gone()
            ivExit.gone()
        }
    }
}
