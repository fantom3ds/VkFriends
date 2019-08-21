package com.example.vkfriends.providers

import android.os.Handler
import com.example.vkfriends.models.FriendModel
import com.example.vkfriends.models.VKUser
import com.example.vkfriends.presenters.FriendsPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException


class FriendsProvider(var presenter: FriendsPresenter) {

    fun testLoadFriends(hasFriends: Boolean) {
        Handler().postDelayed({
            val friends: ArrayList<FriendModel> = ArrayList()
            if (hasFriends) {
                friends.add(
                    FriendModel(
                        name = "Сергей",
                        surname = "Иванов",
                        city = "Татищево",
                        isOnline = true,
                        avatar = "https://pp.userapi.com/c854224/v854224871/ade18/088_v3Ew-IQ.jpg?ava=1"
                    )
                )
                friends.add(
                    FriendModel(
                        name = "Артем",
                        surname = "Смирнов",
                        city = "Саратов",
                        isOnline = false,
                        avatar = "https://sun9-56.userapi.com/c849428/v849428190/1d0ab9/B8D6qbDyqCM.jpg?ava=1"
                    )
                )
            }
            //presenter.friendsLoaded(friends = friends)
        }, 500)
    }

    fun loadFriends(){
        VK.execute(VKFriendsRequest(),object: VKApiCallback<List<VKUser>>{

            override fun fail(error: VKApiExecutionException) {
                presenter.showError(error)
            }

            override fun success(result: List<VKUser>) {
                 presenter.friendsLoaded(result)
            }
        })

    }
}