package com.example.vkfriends.views

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

//аннотация для интерфейса, который будет потом связан библиотекой Moxy
@StateStrategyType(AddToEndSingleStrategy::class)
interface LoginView : MvpView {

    fun setLoading(isLoading: Boolean)
    fun openFriends()
    fun showError(textResource: Int)

}