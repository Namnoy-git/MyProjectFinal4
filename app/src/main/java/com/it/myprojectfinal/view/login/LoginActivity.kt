package com.it.myprojectfinal.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.ResponseLogin
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.view.main.InsertActivity
import com.it.myprojectfinal.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.dialog.view.*

class LoginActivity : AppCompatActivity() {
    var mPreferrences = Preferrences(this)
    var mLoginPersenter = LoginPersenter()
    private var userName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initview()



    }

    private fun initview() {
        if (checkIslogin(mPreferrences.getToken())) {
            val user_id = mPreferrences.getUserId()//เอาไปเรียกใช้ได้้ทุกหน้า

            finish()
            val mIntent = Intent(this, MainActivity::class.java)
            startActivity(mIntent)
        }

        tv_Back_Register.setOnClickListener {
            val i = Intent(this, InsertActivity::class.java)
            startActivity(i)
        }

        btn_Login.setOnClickListener {
            if (edt_username_Login.text.toString()!=""&&edt_Password_Login.text.toString()!="") {
                setapi()
            }
            else if (edt_username_Login.text.toString()!=""&&edt_Password_Login.text.toString()==""){
                dialogMessage("กรุณากรอกรหัสผ่าน")
            }
            else if (edt_username_Login.text.toString()==""&&edt_Password_Login.text.toString()!=""){
                dialogMessage("กรุณากรอกชื่อผู้ใช้")
            }else{
                dialogMessage("กรุณากรอกชื่อผู้ใช้ เเละ รหัสผ่าน")
            }
        }
    }


    private fun setapi() {


            mLoginPersenter.LoginPersenterRx(
                edt_username_Login.text.toString(),
                edt_Password_Login.text.toString(),
                this::onSuccessSubscrib,
                this::onErrorSubscrib
            )


    }


    private fun onSuccessSubscrib(responseLogin: ResponseLogin) {
        userName = responseLogin.message.username

        mPreferrences.saveToken(responseLogin.message.token)
        mPreferrences.saveUserId(responseLogin.message.user_id.toString())


        val mIntent = Intent(this, MainActivity::class.java)

        startActivity(mIntent)

    }

    private fun onErrorSubscrib(Message: String) {
        dialogMessage("รหัสผ่านไม่ถูกต้อง")

    }

    fun checkIslogin(token: String): Boolean {
        return token.isNotEmpty()
    }

    private fun dialogMessage(Message: String) {
        val view = layoutInflater.inflate(R.layout.dialog_login, null)
        view.TV_Mess.setText(Message)
        view.TV_Mess1.setText("ปิด")
        val Dialog = AlertDialog.Builder(this@LoginActivity).apply {
            setTitle("เตือน!!")
            setIcon(R.drawable.warning)
            setView(view)
        }
        val dialogButton = Dialog.show()
        view.TV_Mess1.setOnClickListener {
            dialogButton.dismiss()
        }
    }
}