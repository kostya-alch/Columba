package ui.fragments

import androidx.fragment.app.Fragment
import com.example.columba.R
import com.example.columba.utilits.APP_ACTIVITY
import com.example.columba.utilits.hideKeyboard

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/
class MainFragment : Fragment(R.layout.fragment_chats) {



    override fun onResume() {  // fragment lifecycle
        super.onResume()
        APP_ACTIVITY.title = "Columba"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
    }
}