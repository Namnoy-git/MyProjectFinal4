package com.it.myprojectfinal.ui.notifications

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
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

//        val amphur = listOf("อำเภอเมืองมหาสารคาม","อำเภอแกดำ","อำเภอโกสุมพิสัย","อำเภอกันทรวิชัย","อำเภอเชียงยืน","อำเภอบรบือ")
//        val spinner2 = view.findViewById<Spinner>(R.id.spinneramphor)
//        spinner2?.adapter = activity?.let {
//            ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item,amphur
//            )
//        } as SpinnerAdapter
//        spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                println("erreur")
//
//            }
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val amphur = parent?.getItemAtPosition(position).toString()
////                Toast.makeText(activity,types, Toast.LENGTH_LONG).show()
//                println(amphur)
//            }
//
//        }


        return view.rootView

    }

    private fun setspinner(view: View) {

        InPersenter.GetDataAmphur(

            {

                val amphur = ArrayList<String>()
                for(i in it.data){
                    amphur.add(i.amphur_name)
                }



                val spinner2 = view.findViewById<Spinner>(R.id.spinneramphor)
                spinner2?.adapter = activity?.let {
                    ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item,amphur
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


            {

                val gettambon = ArrayList<String>()
                for(i in it.message){
                    gettambon.add(i.tambon_name)
                }


                val spinner3 = view.findViewById<Spinner>(R.id.spinner_tambon)
                spinner3?.adapter = activity?.let {
                    ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item,gettambon
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

    //    private fun initGoogleMap(savedInstanceState: Bundle?) {
//        // *** IMPORTANT ***
//        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
//        // objects or sub-Bundles.
//        var mapViewBundle: Bundle? = null
//        if (savedInstanceState != null) {
//            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
//        }
//        mMapView!!.onCreate(mapViewBundle)
//        mMapView!!.getMapAsync(this)
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context as Activity)
//    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        var mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY)
//        if (mapViewBundle == null) {
//            mapViewBundle = Bundle()
//            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
//        }
//        mMapView!!.onSaveInstanceState(mapViewBundle)
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mMapView!!.onResume()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mMapView!!.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mMapView!!.onStop()
//    }
//
//
//    override fun onMapReady(map: GoogleMap) {
//        if (ActivityCompat.checkSelfPermission(
//                context as Activity,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            )
//            != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(
//                context as Activity,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            )
//            != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        map.isMyLocationEnabled = true
//
//        fusedLocationClient.lastLocation.addOnSuccessListener(context as Activity) { location ->
//            if (location != null) {
//                lastLocation = location
//                //ดึง lat long  ออกมาจากตำแหน่งปัจุบัน กรณีที่ผูู้แจ้งอยู่ตรงงงที่เกิดเหตุ
//                lat = location.latitude.toString()
//                long = location.longitude.toString()
//                // lat long มันจะถูกเก็บไว้ในตัวแปร ที่นี่เวลาเอาลง database ก็ เอา lat  long ลง
//                val currentLatLng = LatLng(location.latitude, location.longitude)
//                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
//            }
//        }
//
//        map.uiSettings.isZoomControlsEnabled = true
//        map.setOnMapClickListener(object :GoogleMap.OnMapClickListener{
//            override fun onMapClick(p0: LatLng) {
//                //กดเลือกปักหมุด
//                map.addMarker(
//                    MarkerOptions().position(p0).title(
//                    p0.latitude.toString()+","+p0.longitude.toString()
//                ))
//                //ตรงนี้ เด้อ  5555 มันวิธีเดียวกันแต่เขียนไม่เหมือนกัน 555+
//                lat  = p0.latitude.toString()
//                long = p0.longitude.toString()
//                object :GoogleMap.OnMarkerClickListener{
//                    override fun onMarkerClick(p0: Marker?): Boolean {
//                        p0!!.remove()
//                        return true
//                    }
//
//                }
//            }
//
//        })
//        map.setOnMarkerClickListener(object :GoogleMap.OnMarkerClickListener{
//            override fun onMarkerClick(p0: Marker?): Boolean {
//                p0!!.remove()
//                return true
//            }
//        })
//    }
//    override fun onPause() {
//        mMapView!!.onPause()
//        super.onPause()
//    }
//
//    override fun onDestroy() {
//        mMapView!!.onDestroy()
//        super.onDestroy()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mMapView!!.onLowMemory()
//    }
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






