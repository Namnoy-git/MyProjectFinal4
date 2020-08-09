package com.it.myprojectfinal.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.DataList

import com.it.myprojectfinal.model.response.ResponseGetNoti
import com.it.myprojectfinal.model.response.ResponseProfileBody
import com.it.myprojectfinal.rest.local.Preferrences.Companion.FILENAME
import com.it.myprojectfinal.ui.adapter.AdapterDataNoti
import com.it.myprojectfinal.ui.notifications.PresenterFragment
import com.it.myprojectfinal.view.main.MainPresenter
import com.it.myprojectfinal.view.main.ShowDataNoti
import kotlinx.android.synthetic.main.activity_show_data_noti.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.itemdatanoti.*

class HomeFragment : Fragment() {

    lateinit var pref: SharedPreferences
    var userID = ""
    var notiPersenter = PresenterFragment()
    var nResponsenoti = ArrayList<DataList>()
    //    var nSeeuser = ArrayList<ResponseProfileBody>()
    val selectProfile = MainPresenter()
    private lateinit var homeViewModel: HomeViewModel
    lateinit var notiAdapterData: AdapterDataNoti
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        pref = context?.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)!!

        userID = pref.getString("user_id", "") ?: ""


        return view.rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setadapter(view)
        setapi()//listnoti
        setApiuser()//userid
    }

    private fun setApiuser() {
        selectProfile.SelectUser(

            userID,
            {
                Tv_Uname.text = it.message.user_name
            },
            {

            }

        )
    }

    private fun setadapter(view: View) {
        notiAdapterData =
            AdapterDataNoti(requireContext(), nResponsenoti) { notic_id, notic_topic, notic_detail,
                                                               notic_type, notic_voilent, notic_amphur,notic_tambon, notic_status, notic_steps, notic_lat, notic_long, notic_time ->
                val i = Intent(context, ShowDataNoti::class.java)
                i.putExtra("notic_id", notic_id)
                i.putExtra("notic_topic", notic_topic)
                i.putExtra("notic_detail", notic_detail)
                i.putExtra("notic_type", notic_type)
                i.putExtra("notic_voilent", notic_voilent)
                i.putExtra("notic_amphur", notic_amphur)
                i.putExtra("notic_tambon", notic_tambon)
                i.putExtra("notic_status", notic_status)
                i.putExtra("notic_steps", notic_steps)
                i.putExtra("notic_lat", notic_lat)
                i.putExtra("notic_long", notic_long)
                i.putExtra("notic_time", notic_time)
                startActivity(i)
            }
        view.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notiAdapterData
            notiAdapterData.notifyDataSetChanged()
        }


    }

    private fun setapi() {
        notiPersenter.GetNotiPersenterRx(
            userID,
            this::onSuccessSub,
            this::onErrorSub
        )


    }


    private fun onSuccessSub(response: ResponseGetNoti) {
        nResponsenoti.clear()
        for (i in response.data.indices) {
            nResponsenoti.add(response.data[i])
        }
        notiAdapterData.Updatedata(nResponsenoti)

    }

    private fun onErrorSub(message: String) {


    }


}
