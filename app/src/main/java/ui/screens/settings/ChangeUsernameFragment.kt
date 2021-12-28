package ui.screens.settings

import com.example.columba.R
import com.example.columba.database.*
import com.example.columba.utilits.AppValueEventListener
import com.example.columba.utilits.showToast
import kotlinx.android.synthetic.main.fragment_change_username.*
import ui.screens.BaseChangeFragment
import java.util.*

/* Фрагмент для изменения username пользователя */
class ChangeUsernameFragment : BaseChangeFragment(R.layout.fragment_change_username) {

    lateinit var mNewUsername: String

    override fun onResume() {
        super.onResume()
        settings_input_username.setText(USER.username)

    }

    override fun change() {
        mNewUsername = settings_input_username.text.toString().toLowerCase(Locale.getDefault())
        if (mNewUsername.isEmpty()) {
            showToast("Поле пустое, введите ник")
        } else {
            REF_DATABASE_ROOT.child(
                NODE_USERNAMES
            )
                .addListenerForSingleValueEvent(AppValueEventListener {
                    if (it.hasChild(mNewUsername)) {
                        showToast("Такой пользователь уже есть")
                    } else {
                        changeUsername()
                    }
                })

        }
    }

    private fun changeUsername() {
        /* Изменение username в базе данных */
        REF_DATABASE_ROOT.child(NODE_USERNAMES).child(mNewUsername).setValue(
            CURRENT_UID
        ).addOnCompleteListener {
                if (it.isSuccessful) {
                    updateCurrentUsername(mNewUsername)
                }
            }
    }


}