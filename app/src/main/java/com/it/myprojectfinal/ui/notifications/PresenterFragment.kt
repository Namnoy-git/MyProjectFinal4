package com.it.myprojectfinal.ui.notifications

import android.util.Log
import androidx.fragment.app.Fragment
import com.it.myprojectfinal.model.body.BodyGetUserNoti

import com.it.myprojectfinal.model.body.BodyInsertNoti
import com.it.myprojectfinal.model.response.ResponseGetNoti
import com.it.myprojectfinal.model.response.ResponseInsertNoti
import com.it.myprojectfinal.rest.DataModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class PresenterFragment : Fragment() {

    var mDisposable: Disposable? = null

    fun InsertNotiMainPersenterRx(
        user_id: String,
        notic_topic: String,
        notic_detail: String,
        notic_location: String,
        notic_type: String,
        notic_voilent: String,
        dataResponse: (ResponseInsertNoti) -> Unit,
        MessageError: (String) -> Unit
    ) {
        mDisposable =
            DataModule.myAppService.doInsertnoti(
                BodyInsertNoti(
                    user_id,
                    notic_topic,
                    notic_detail,
                    notic_location,
                    notic_type,
                    notic_voilent
                )
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseInsertNoti>() {

                    override fun onComplete() {

                    }

                    override fun onNext(response: ResponseInsertNoti) {
                        Log.d("messageInsertNoti", response.toString())
                        dataResponse.invoke(response)

                    }

                    override fun onError(e: Throwable) {
                        Log.d("messageInsertNoti", e.message!!.toString())
                        MessageError.invoke(e.message!!)
                    }
                })

    }

    fun GetNotiPersenterRx(
        user_id: String,
        datarResponse: (ResponseGetNoti) -> Unit,
        MessageError: (String) -> Unit
    ) {
        mDisposable = DataModule.myAppService.doGetNoti(BodyGetUserNoti(user_id))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ResponseGetNoti>() {

                override fun onComplete() {

                }

                override fun onNext(response: ResponseGetNoti) {
                    Log.d("messagegetnoti", response.toString())
                    datarResponse.invoke(response)

                }

                override fun onError(e: Throwable) {
                    MessageError.invoke(e.message!!)
                }
            })

    }
}
