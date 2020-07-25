package com.it.myprojectfinal.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.ResponseLogin
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.view.main.InsertActivity
import com.it.myprojectfinal.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var mPreferrences = Preferrences(this)
    var mLoginPersenter = LoginPersenter ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initview()
        setapi()





    }

    private fun initview() {
        if (checkIslogin(mPreferrences.getToken())){
            val user_id = mPreferrences.getUserId()//เอาไปเรียกใช้ได้้ทุกหน้า

            finish()
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
        }

        tv_Back_Register.setOnClickListener {
            val i = Intent(this, InsertActivity::class.java)
            startActivity(i)
        }
    }




    private fun setapi() {
        btn_Login.setOnClickListener{

            mLoginPersenter.LoginPersenterRx(
                edt_username_Login.text.toString(),
                edt_Password_Login.text.toString(),
                this::onSuccessSubscrib,
                this::onErrorSubscrib)

        }

    }


    private fun onSuccessSubscrib(responseLogin: ResponseLogin) {

        mPreferrences.saveToken(responseLogin.message.token)
        mPreferrences.saveUserId(responseLogin.message.user_id.toString())

        val mIntent = Intent(this,MainActivity::class.java)

        startActivity(mIntent)
    }

    private fun onErrorSubscrib(Message:String) {

    }

    fun checkIslogin(token:String):Boolean{
        return  token.isNotEmpty()
    }

}