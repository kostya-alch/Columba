package com.example.columba.utilits

import android.content.Intent
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.columba.R
import com.example.columba.activities.RegisterActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_settings.*
import ui.fragments.ChatsFragment

// Extension functions
/* File for storing utility functions available throughout the application */
fun showToast(message:String) {
    /* The function shows the message */
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    /* Extension function for AppCompatActivity, allows you to run activiti */
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, addStack: Boolean = true ) {
    /* Extension function for AppCompatActivity, allows you to install fragments */
    if(addStack) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.dataContainer,
                fragment
            ).commit()
    } else {
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer,
                fragment
            ).commit()
    }

}


fun Fragment.replaceFragment(fragment: Fragment) {
    // fragment extension function, allows you to install fragments
    this.fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.dataContainer,
            fragment
        )?.commit()
}

fun hideKeyboard(){
    // the function hides the keyboard
    val imm: InputMethodManager = APP_ACTIVITY.getSystemService(android.content.Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(APP_ACTIVITY.window.decorView.windowToken,0)

}

fun ImageView.downloadAndSetImage(url:String){
    // ImageView extension function, downloads and installs the image
    Picasso.get()
        .load(url)
        .fit()
        .placeholder(R.drawable.default_photo)
        .into(this)
}