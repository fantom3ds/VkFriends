package com.example.vkfriends.presenters

import android.content.Intent
import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.vkfriends.R
import com.example.vkfriends.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException


//Для восстановления состояния (поля ввода и т.д.)
@InjectViewState
class LoginPresenter : MvpPresenter<LoginView>() {

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?):Boolean {
        if (!VK.onActivityResult(requestCode = requestCode, resultCode = resultCode, data = data, callback = object : VKAuthCallback {

                override fun onLogin(token: VKAccessToken) {
                    viewState.openFriends()
                    //сюда можно присобачить сохранялку токена
                    //или не нужно, есть же функция isLogged
                }

                override fun onLoginFailed(errorCode: Int) {
                    viewState.showError(textResource = R.string.login_error_notification)
                }


            })){
            return false
        }
        return true
    }

}