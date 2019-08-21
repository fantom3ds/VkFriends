package com.example.vkfriends.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vkfriends.R
import com.example.vkfriends.models.VKUser
import com.example.vkfriends.providers.FriendsProvider
import com.example.vkfriends.views.FriendsView
import com.vk.api.sdk.exceptions.VKApiExecutionException


@InjectViewState
class FriendsPresenter : MvpPresenter<FriendsView>() {


    fun loadFriends() {
        viewState.setLoading(true)

        FriendsProvider(presenter = this).loadFriends()


    }

    fun friendsLoaded(friends: List<VKUser>) {
        viewState.setLoading(false)
        if (friends.size == 0) {
            viewState.setEmptyList()
            viewState.showError(textResource = R.string.no_friends_notification)
        } else {
            viewState.setFriendsList(friends = friends)
        }
    }

    fun showError(error: VKApiExecutionException){
        viewState.showError(R.string.list_error_notification)
    }

}