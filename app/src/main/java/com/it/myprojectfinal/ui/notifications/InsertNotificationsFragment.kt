package com.it.myprojectfinal.ui.notifications

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.ResponseInsertNoti
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.rest.local.Preferrences.Companion.FILENAME
import com.it.myprojectfinal.ui.home.HomeFragment
import com.it.myprojectfinal.view.main.MainActivity
import com.it.myprojectfinal.view.main.ShowDataNoti2
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class InsertNotificationsFragment : Fragment() {

    lateinit var pref: SharedPreferences
    var userID = ""

    val InPersenter = PresenterFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        pref = context?.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)!!

        userID = pref.getString("user_id", "") ?: ""

        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        setapi(view)

        val types = listOf(
            "อุทกภัย",
            "ภัยแล้ง",
            "วาตภัย",
            "ภัยหนาว",
            "ไฟป่า",
            "อัคคีภัย",
            "แผ่นดินไหว",
            "ดินถล่ม/โคลนถล่ม",
            "อาคารถล่ม/ทรุด",
            "ภัยจากการคมนาคมขนส่ง",
            "ภัยสารเคมี",
            "วัตถุอันตราย",
            "ภัยจากการก่อกวนความไม่สงบ/วินาศกรรม",
            "ภัยทางธรรมชาติ",
            "ภัยอื่นๆ"
        )
        val spinner = view.findViewById<Spinner>(R.id.spinner_type)
        spinner?.adapter = activity?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                types
            )
        } as SpinnerAdapter
        spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val types = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(activity,types, Toast.LENGTH_LONG).show()
                println(types)
            }

        }

        val level = listOf("น้อย", "ปานกลาง", "มาก", "มากที่สุด")

        val spinner1 = view.findViewById<Spinner>(R.id.spinner_leval)
        spinner1?.adapter = activity?.let {
            ArrayAdapter(
                it,
                R.layout.support_simple_spinner_dropdown_item,
                level
            )
        } as SpinnerAdapter
        spinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("erreur")

            }


            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                val types = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(activity,types, Toast.LENGTH_LONG).show()
                println(types)
            }

        }

        return view.rootView

    }

    private fun setapi(view: View) {


        view.buttonNext.setOnClickListener {
            InPersenter.InsertNotiMainPersenterRx(
                userID,
                edt_topic.text.toString(),
                spinner_type.selectedItem.toString(),
                spinner_leval.selectedItem.toString(),
                edit_detail.text.toString(),
                edit_location.text.toString(),
//                edit5.text.toString()
//                edit8.text.toString()
//                edit9.text.toString()

                this::onSuccessSubscribe,
                this::onErrorSubscribe
            )

        }

    }

    private fun onSuccessSubscribe(response: ResponseInsertNoti) {

        Log.d("kkslspsa", response.notic_id.toString())
        val i = Intent(context, ShowDataNoti2::class.java)
        i.putExtra("notic_id", response.notic_id)
        /*i.putExtra("topic", edt_topic.text.toString())
        i.putExtra("type", spinner_type.selectedItem.toString())
        i.putExtra("level", spinner_leval.selectedItem.toString())
        i.putExtra("detail", edit_detail.text.toString())
        i.putExtra("location", edit_location.text.toString())*/
        startActivity(i)

    }

    private fun onErrorSubscribe(message: String) {
    }
}




