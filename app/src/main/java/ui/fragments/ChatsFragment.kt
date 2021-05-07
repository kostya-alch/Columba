package ui.fragments

import androidx.fragment.app.Fragment
import com.example.columba.R
import com.example.columba.utilits.APP_ACTIVITY

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/
class ChatsFragment : Fragment(R.layout.fragment_chats) {



    override fun onResume() {  // fragment lifecycle
        super.onResume()
        APP_ACTIVITY.title = "Чаты"
    }
}