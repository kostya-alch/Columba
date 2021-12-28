package ui.screens.settings

import com.example.columba.R
import com.example.columba.database.USER
import com.example.columba.database.setNameToDatabase
import com.example.columba.utilits.showToast
import kotlinx.android.synthetic.main.fragment_change_name.*
import ui.screens.BaseChangeFragment

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
            setNameToDatabase(fullname)

        }
    }
}