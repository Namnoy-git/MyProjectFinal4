package com.it.myprojectfinal.view.login

import android.util.Log
import com.it.myprojectfinal.model.body.BodyLogin
import com.it.myprojectfinal.model.response.ResponseLogin
import com.it.myprojectfinal.rest.DataModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class LoginPersenter {

    var mDisposable: Disposable? = null



    fun LoginPersenterRx(
        user:String, pass:String,
        datarResponse:(ResponseLogin)->Unit,
        MessageError:(String)->Unit
    ){
        mDisposable = DataModule.myAppService.doLogin(BodyLogin(user,pass))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseLogin>(){

                override fun onComplete() {

                }

                override fun onNext(responselogin: ResponseLogin) {
                    Log.d("messageLoginxxxxxx",responselogin.toString())
                    datarResponse.invoke(responselogin)

                }

                override fun onError(e: Throwable) {
                    Log.d("messageLoginxxxxxx",e.message.toString())
                    MessageError.invoke(e.message!!)
                }
            })

    }
}


