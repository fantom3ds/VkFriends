package com.example.vkfriends.providers

import com.example.vkfriends.models.VKUser
import com.example.vkfriends.presenters.FriendsPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException


class FriendsProvider(var presenter: FriendsPresenter) {

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