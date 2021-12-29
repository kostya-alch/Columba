package ui.screens.groups

import androidx.recyclerview.widget.RecyclerView
import com.example.columba.R
import com.example.columba.models.CommonModel
import com.example.columba.utilits.APP_ACTIVITY
import com.example.columba.utilits.hideKeyboard
import com.example.columba.utilits.showToast
import kotlinx.android.synthetic.main.fragment_create_group.*
import ui.screens.base.BaseFragment

class CreateGroupFragment(private var listContacts: List<CommonModel>) :
    BaseFragment(R.layout.fragment_create_group) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: AddContactsAdapter

    override fun onResume() {  // fragment lifecycle
        super.onResume()
        APP_ACTIVITY.title = getString(R.string.create_group)
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        initRecycleView()
        create_group_btn_complete.setOnClickListener {
            showToast("Click")
        }
        create_group_input.requestFocus()
    }

    private fun initRecycleView() {
        mRecyclerView = create_group_recycle_view
        mAdapter = AddContactsAdapter()
        mRecyclerView.adapter = mAdapter
        listContacts.forEach { mAdapter.updateListItems(it) }

    }
}