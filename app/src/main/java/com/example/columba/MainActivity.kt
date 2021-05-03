package com.example.columba

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.columba.activities.RegisterActivity
import com.example.columba.databinding.ActivityMainBinding
import com.example.columba.models.User
import com.example.columba.utilits.*
import com.google.firebase.database.core.Context
import com.theartofdev.edmodo.cropper.CropImage
import ui.fragments.ChatsFragment
import ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding: ActivityMainBinding // initializing objects
    private lateinit var mToolbar: Toolbar
    lateinit var mAppDrawer: AppDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        initFirebase()
        initUser {
            initContacts()
            initFields()
            initFunc()
        }

    }

    private fun initContacts() {
        if (checkPermissions(READ_CONTACTS)) {
            showToast("Чтение контактов")
        }
    }


    private fun initFunc() { // all MainActivity functionality
        if (AUTH.currentUser != null) { //checking for user authorization

            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), false)
        } else { // run register procedure
            replaceActivity(RegisterActivity())

        }
    }


    private fun initFields() {
        mToolbar = mBinding.mainToolbar
        mAppDrawer = AppDrawer(this, mToolbar)

    }

    override fun onStart() {
        super.onStart()
        AppStates.updateState(AppStates.ONLINE)
    }

    override fun onStop() {
        super.onStop()
        AppStates.updateState(AppStates.OFFLINE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ContextCompat.checkSelfPermission(APP_ACTIVITY, READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            initContacts()
        }
    }
}