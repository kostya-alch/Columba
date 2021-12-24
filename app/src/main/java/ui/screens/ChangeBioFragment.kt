package ui.screens

import com.example.columba.R
import com.example.columba.database.USER
import com.example.columba.database.setBioToDatabase
import kotlinx.android.synthetic.main.fragment_change_bio.*

/* Фрагмент для изменения информации о пользователе */
class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
    }


}