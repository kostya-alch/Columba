package ui.fragments

import com.example.columba.R
import com.example.columba.utilits.*
import kotlinx.android.synthetic.main.fragment_change_name.*

// fragment for changing the user name
class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {


    override fun onResume() {
        super.onResume()


    }

    private fun initFullnameList() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            settings_input_name.setText(fullnameList[0])
            settings_input_surname.setText(fullnameList[1]) // displaying the name and surname in the name change
        } else {
            settings_input_name.setText(fullnameList[0])
        }
    }

    override fun change() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()
        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullname = "$name $surname"
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_FULLNAME)
                .setValue(fullname).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(getString(R.string.toast_data_update))
                        USER.fullname = fullname
                        APP_ACTIVITY.mAppDrawer.updateHeader()
                        fragmentManager?.popBackStack()
                    }
                }
        }
    }
}