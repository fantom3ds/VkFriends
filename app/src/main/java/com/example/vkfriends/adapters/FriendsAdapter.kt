package com.example.vkfriends.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vkfriends.R
import com.example.vkfriends.models.VKUser
import kotlinx.android.synthetic.main.friend_item.view.*

class FriendsAdapter : RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    private var sourceFriends: ArrayList<VKUser> = ArrayList()
    private var friends: ArrayList<VKUser> = ArrayList()

    fun setFriends(friendList: List<VKUser>) {
        sourceFriends.clear()
        sourceFriends.addAll(friendList)
        filter(query = "")
    }

    fun filter(query: String) {
        friends.clear()
        sourceFriends.forEach {
            if (it.firstName.contains(query, ignoreCase = true) || it.lastName.contains(query, ignoreCase = true)) {
                friends.add(it)
            } else {
//                it.city?.let { city ->
//                    if (city.contains(query, ignoreCase = true)) {
//                        friends.add(it)
//                    }
//                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(friend = friends[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false))
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(friend: VKUser) {
            //Обращаемся к ячейке
            itemView.apply {
                //Аватар друга
                friend.photo?.let { url -> Glide.with(context).load(url).into(civ_user_photo) }

                //Имя друга
                tv_user_name.text = "${friend.firstName} ${friend.lastName}"
                //Город друга
                tv_user_city.text = ""
                //friend.city?.let { city -> tv_user_city.text = city }

                //Онлайн друга
                if (friend.isOnline == 1)
                    v_online_flag.visibility = View.VISIBLE
                else
                    v_online_flag.visibility = View.GONE

            }
        }
    }
}