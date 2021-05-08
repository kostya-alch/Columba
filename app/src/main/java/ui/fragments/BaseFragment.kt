package ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.example.columba.MainActivity
import com.example.columba.R
import com.example.columba.databinding.FragmentChatsBinding
import com.example.columba.utilits.APP_ACTIVITY
/* Базовый фрагмент, от него наследуются все фрагменты приложения, кроме главного */
open class BaseFragment( layout:Int) : Fragment (layout) {



    override fun onStart() {
        super.onStart()
        APP_ACTIVITY.mAppDrawer.disabledDrawer()

    }
}
