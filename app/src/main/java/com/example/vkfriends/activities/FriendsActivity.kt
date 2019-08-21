package com.example.vkfriends.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.vkfriends.R
import com.example.vkfriends.adapters.FriendsAdapter
import com.example.vkfriends.models.VKUser
import com.example.vkfriends.presenters.FriendsPresenter
import com.example.vkfriends.views.FriendsView
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : MvpAppCompatActivity(), FriendsView {

    @InjectPresenter
    lateinit var presenter: FriendsPresenter

    private var adapter = FriendsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        presenter.loadFriends()

        rv_friends.layoutManager = LinearLayoutManager(applicationContext)
        rv_friends.adapter = adapter
        rv_friends.setHasFixedSize(true)

        et_search.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Фильтруем данные
                adapter.filter(p0.toString())
            }

        })
    }


    override fun showError(textResource: Int) {
        tv_no_items.text = getString(textResource)
    }

    override fun setEmptyList() {
        rv_friends.visibility = View.GONE
        tv_no_items.visibility = View.VISIBLE
    }

    override fun setFriendsList(friends: List<VKUser>) {
        rv_friends.visibility = View.VISIBLE
        tv_no_items.visibility = View.GONE

        adapter.setFriends(friends)
    }

    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            rv_friends.visibility = View.GONE
            tv_no_items.visibility = View.GONE
            cpv_friends.visibility = View.VISIBLE
        } else {
            cpv_friends.visibility = View.GONE
        }
    }
}
