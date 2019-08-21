package com.example.vkfriends.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.vkfriends.R
import com.example.vkfriends.presenters.LoginPresenter
import com.example.vkfriends.views.LoginView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : MvpAppCompatActivity(), LoginView {

    private val TAG = this::class.java.simpleName

    //Инжектируем презентер, он определяется автоматически Moxy
    @InjectPresenter
    lateinit var presenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Если мы залогинены, то переходим сразу к друзьяшкам
        if (VK.isLoggedIn()) {
            startActivity(Intent(this, FriendsActivity::class.java))
            finish()
        }

        btn_login.setOnClickListener {
            //Открывается активити с логином
            //Результат возвращается в onActivityResult
            VK.login(this@LoginActivity, listOf(VKScope.FRIENDS))
        }

        //Это получение отпечатка сертификата приложения, нужно было в начале, при настройке
//        val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)
//        Log.e(TAG, "${fingerprints?.get(0)}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Пусть презентер разбирает результат логина, создаем функцию
        if (!presenter.loginVk(requestCode = requestCode, resultCode = resultCode, data = data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }

    }


    override fun setLoading(isLoading: Boolean) {
        if (isLoading) {
            btn_login.visibility = View.GONE
            cpv_login.visibility = View.VISIBLE
        } else {
            btn_login.visibility = View.VISIBLE
            cpv_login.visibility = View.GONE
        }
    }

    override fun openFriends() {
        startActivity(Intent(applicationContext, FriendsActivity::class.java))
        finish()
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_LONG).show()
    }
}
