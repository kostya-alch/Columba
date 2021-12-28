package ui.screens.main_list

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.columba.R
import com.example.columba.database.*
import com.example.columba.models.CommonModel
import com.example.columba.utilits.APP_ACTIVITY
import com.example.columba.utilits.AppValueEventListener
import com.example.columba.utilits.hideKeyboard
import kotlinx.android.synthetic.main.fragment_main_list.*

/* Главный фрагмент, содержит все чаты, группы и каналы с которыми взаимодействует пользователь*/
class MainListFragment : Fragment(R.layout.fragment_main_list) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: MainListAdapter
    private val mRefMainList = REF_DATABASE_ROOT.child(NODE_MAIN_LIST).child(CURRENT_UID)
    private val mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS)
    private val mRefMessages = REF_DATABASE_ROOT.child(NODE_MESSAGES).child(CURRENT_UID)
    private var mListItems = listOf<CommonModel>()

    override fun onResume() {  // fragment lifecycle
        super.onResume()
        APP_ACTIVITY.title = "Columba"
        APP_ACTIVITY.mAppDrawer.enableDrawer()
        hideKeyboard()
        initRecycleView()
    }

    private fun initRecycleView() {
        mRecyclerView = main_list_recycle_view
        mAdapter = MainListAdapter()
        // первый запрос
        mRefMainList.addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot ->
            mListItems = dataSnapshot.children.map { it.getCommonModel() }
            mListItems.forEach { model ->

                // второй запрос
                mRefUsers.child(model.id)
                    .addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot1 ->
                        val newModel = dataSnapshot1.getCommonModel()

                        // третий запрос
                        mRefMessages.child(model.id).limitToLast(1)
                            .addListenerForSingleValueEvent(AppValueEventListener { dataSnapshot2 ->
                                val tempList = dataSnapshot2.children.map { it.getCommonModel() }

                                if (tempList.isEmpty()) {
                                    newModel.lastMessage = "Чат очищен"
                                } else {
                                    newModel.lastMessage = tempList[0].text
                                }


                                if (newModel.fullname.isEmpty()) {
                                    newModel.fullname = newModel.phone
                                }
                                mAdapter.updateListItems(newModel)
                            })
                    })
            }
        })
        mRecyclerView.adapter = mAdapter
    }
}