package com.example.columba.utilits

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AppValueEventListener(val onSuccess: (DataSnapshot) -> Unit) : ValueEventListener {
    override fun onCancelled(error: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        onSuccess(p0)
    }

}