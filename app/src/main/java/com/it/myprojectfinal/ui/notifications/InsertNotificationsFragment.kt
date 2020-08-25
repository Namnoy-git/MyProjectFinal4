package com.it.myprojectfinal.ui.notifications

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.MapView
import com.it.myprojectfinal.R
import com.it.myprojectfinal.model.response.*

import com.it.myprojectfinal.rest.local.Preferrences.Companion.FILENAME
import com.it.myprojectfinal.view.main.MapsActivity
import com.it.myprojectfinal.view.main.ShowDataNoti2
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*

class InsertNotificationsFragment : Fragment() {

    lateinit var pref: SharedPreferences
    var userID = ""
    private var CODE = 999
    private var lat = ""
    private var long = ""
    val InPersenter = PresenterFragment()

    var MAPVIEW_BUNDLE_KEY = "AIzaSyBXg9itwAb3vI2nI58qiC2ivb-fRp2Tzuo"
    private var mMapView: MapView? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location

    //ประกาศตัวแปรรับ lat long
    private var gettambon = ArrayList<Data>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        pref = context?.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)!!

        userID = pref.getString("user_id", "") ?: ""


        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
//        mMapView = view.findViewById(R.id.mapview)
        setapi(view)
        setspinner(view)
        view.map_Next.setOnClickListener {

            val im = Intent(context, MapsActivity::class.java)
            startActivityForResult(im, CODE)
        }


//        initGoogleMap(savedInstanceState)
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
//        Toast.makeText(context, view.spinneramphor.selectedItemId.toString(), Toast.LENGTH_SHORT).show()
        return view.rootView

    }

    private fun setspinner(view: View) {
        var amphurID = ""
        InPersenter.GetDataAmphur(

            {

                val amphur = ArrayList<String>()
                for (i in it.data) {
                    amphur.add(i.amphur_name)
                }

//                for (i in it.data) {
//                    amphurID = i.amphur_id
//                }


                val spinner2 = view.findViewById<Spinner>(R.id.spinneramphor)
                spinner2?.adapter = activity?.let {
                    ArrayAdapter(
                        it, R.layout.support_simple_spinner_dropdown_item, amphur
                    )
                } as SpinnerAdapter
                spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        println("erreur")

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val amphur = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(activity,types, Toast.LENGTH_LONG).show()
                        println(amphur)
                    }
                }
            },
            {

            }
        )
        InPersenter.GetDataTambon(
//            amphurID,
            {

                val gettambon = ArrayList<String>()
                for (i in it.data) {
                    gettambon.add(i.tambon_name)
                }


                val spinner3 = view.findViewById<Spinner>(R.id.spinner_tambon)
                spinner3?.adapter = activity?.let {
                    ArrayAdapter(
                        it, R.layout.support_simple_spinner_dropdown_item, gettambon
                    )
                } as SpinnerAdapter
                spinner3?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        println("erreur")

                    }
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val amphur = parent?.getItemAtPosition(position).toString()
//                Toast.makeText(activity,types, Toast.LENGTH_LONG).show()
                        println(amphur)
                    }

                }

            },
            {

            }


        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var Data_lat = data!!.getStringExtra("lat")
        var Data_long = data!!.getStringExtra("long")
        lat = Data_lat
        long = Data_long
        Toast.makeText(context, lat + " - " + long, Toast.LENGTH_SHORT).show()
        super.onActivityResult(requestCode, resultCode, data)


        lat_show.text = lat
        long_show.text = long
    }

    private fun setapi(view: View) {


        view.buttonNext.setOnClickListener {
            InPersenter.InsertNotiMainPersenterRx(

                userID,
                edt_topic.text.toString(),
                spinner_type.selectedItem.toString(),
                spinner_leval.selectedItem.toString(),
                edit_detail.text.toString(),
                spinneramphor.selectedItem.toString(),
                spinner_tambon.selectedItem.toString(),
                lat,
                long,
                this::onSuccessSubscribe,
                this::onErrorSubscribe
            )

        }

    }

    private fun onSuccessSubscribe(response: ResponseInsertNoti) {

        Log.d("kkslspsa", response.notic_id.toString())

//        val smsMan = SmsManager.getDefault()
//        val sendTo = "0981721044"
//        val message = "ท่านได้ทำการเเจ้งเหตุเเล้ว"
//        smsMan.sendTextMessage(sendTo,null, message, null, null)


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






