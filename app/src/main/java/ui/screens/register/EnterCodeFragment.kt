package ui.screens.register

import androidx.fragment.app.Fragment
import com.example.columba.R
import com.example.columba.database.*
import com.example.columba.utilits.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

/* Фрагмент для ввода кода подтверждения при регистрации */
class EnterCodeFragment(val mPhoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {


    override fun onStart() { //lambda function for checking that the user entered 6 digits in the code
        super.onStart()
        APP_ACTIVITY.title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher {

            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })
    }

    private fun enterCode() { // enter the database
        /* Функция проверяет код, если все нормально, производит создания информации о пользователе в базе данных.*/
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String, Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = mPhoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid)
                    .addListenerForSingleValueEvent(AppValueEventListener {
                        if (!it.hasChild(CHILD_USERNAME)) {
                            dateMap[CHILD_USERNAME] = uid
                        }
                        REF_DATABASE_ROOT.child(
                            NODE_PHONES
                        ).child(mPhoneNumber).setValue(uid)
                            .addOnFailureListener { showToast(it.message.toString()) }
                            .addOnSuccessListener { }
                        REF_DATABASE_ROOT.child(
                            NODE_USERS
                        ).child(uid).updateChildren(dateMap)
                            .addOnSuccessListener {
                                showToast("Добро пожаловать ")
                                restartActivity()
                            }
                            .addOnFailureListener { showToast(it.message.toString()) }

                    })


            } else showToast(task.exception?.message.toString())
        }
    }
}