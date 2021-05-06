package com.example.columba.utilits
/* Класс перечисление состояний приложения*/
enum class AppStates(val state:String) {
    ONLINE("В сети"),
    OFFLINE("был недавно"),
    TYPING("печатает");

    companion object {
        fun updateState(appStates: AppStates){
            /*Функция принимает состояние и записывает в базу данных*/
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID).child(CHILD_STATE)
                .setValue(appStates.state)
                .addOnSuccessListener { USER.state = appStates.state }
                .addOnFailureListener {showToast(it.message.toString())}
        }
    }
}