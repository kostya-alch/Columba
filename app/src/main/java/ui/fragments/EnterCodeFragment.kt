package ui.fragments

import androidx.fragment.app.Fragment
import com.example.columba.MainActivity
import com.example.columba.R
import com.example.columba.activities.RegisterActivity
import com.example.columba.utilits.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*


class EnterCodeFragment(val mPhoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {



    override fun onStart() { //lambda function for checking that the user entered 6 digits in the code
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener ( AppTextWatcher {

                val string = register_input_code.text.toString()
                if (string.length ==6) {
                    enterCode()
                }
        })
    }
    private fun enterCode(){ // enter the database
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id,code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String,Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = mPhoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener{ task2 ->
                        if (task2.isSuccessful){
                            showToast("Добро пожаловать ")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        } else {
                            showToast(task2.exception?.message.toString())
                        }

                }
            } else showToast(task.exception?.message.toString())
        }
    }
}