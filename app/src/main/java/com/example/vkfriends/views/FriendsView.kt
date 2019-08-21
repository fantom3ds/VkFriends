package com.example.vkfriends.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.vkfriends.models.VKUser

//AddToEndSingleStrategy::class
@StateStrategyType(AddToEndSingleStrategy::class)
interface FriendsView : MvpView {
    fun showError(textResource: Int)
    //Показать пустой лист
    fun setEmptyList()

    //Показать друзей
    fun setFriendsList(friends: List<VKUser>)

    fun setLoading(isLoading:Boolean)
}