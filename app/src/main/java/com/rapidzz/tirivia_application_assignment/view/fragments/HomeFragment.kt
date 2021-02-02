package com.rapidzz.tirivia_application_assignment.view.fragments

import androidx.lifecycle.observe
import com.rapidzz.tirivia_application_assignment.R
import com.rapidzz.tirivia_application_assignment.models.dataModels.generalModels.CategoryModel
import com.rapidzz.tirivia_application_assignment.utils.application.obtainViewModel
import com.rapidzz.tirivia_application_assignment.utils.generalUtils.AppInstance
import com.rapidzz.tirivia_application_assignment.view.activities.MainActivity
import com.rapidzz.tirivia_application_assignment.view.adapters.BaseAdapter
import com.rapidzz.tirivia_application_assignment.view.adapters.CategoryListAdapter
import com.rapidzz.tirivia_application_assignment.view.dialogs.ConfirmationDialog
import com.rapidzz.tirivia_application_assignment.view.dialogs.SelectQuestionTypeDialog
import com.rapidzz.tirivia_application_assignment.viewModels.CategoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home), BaseAdapter.OnItemClicker {


    val categoryList: ArrayList<CategoryModel> = ArrayList()
    var selectedCategory: CategoryModel? = null
    private val viewModel: CategoriesViewModel by lazy { obtainViewModel(CategoriesViewModel::class.java) }


    override fun attachViewModel() {

        with(viewModel)
        {
            allCategoriesLiveData.observe(viewLifecycleOwner) {
                if (it.isNullOrEmpty()) {
                      insertAllCategories(AppInstance.getCategoryList())
                } else {
                    categoryList.clear()
                    categoryList.addAll(it)
                    rvCategory.adapter = CategoryListAdapter(categoryList,this@HomeFragment)
                }
            }
        }
    }


    override fun initViews() {
        setToolbarTitle(getString(R.string.category_list), false, true)
        (activity as MainActivity).ivExit.setOnClickListener {
            confirmToExit()
        }
    }

    override fun onItemClick(position: Int, data: Any) {
        selectedCategory = categoryList[position]
        SelectQuestionTypeDialog.newInstance().show(childFragmentManager, "")
    }


    fun openQuestionWithSelections(difficultyLevel: String, optionType: String) {
        selectedCategory?.let {
            navigateToFragment(
                HomeFragmentDirections.actionHomeFragmentToQuestionListFragment(
                    optionType,
                    difficultyLevel,
                    it
                )
            )
        }

    }

    fun confirmToExit() {
        ConfirmationDialog(false,
            getString(R.string.sure_to_exit_application),
            getString(R.string.alert),
            object : ConfirmationDialog.ConfirmationListener {
                override fun isConfirmed(isConfirmed: Boolean) {
                    if (isConfirmed) {
                        activity?.finish()
                    }
                }
            }).show(childFragmentManager, "")
    }
}