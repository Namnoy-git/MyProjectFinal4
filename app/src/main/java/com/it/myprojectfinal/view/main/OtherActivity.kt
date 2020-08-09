package com.it.myprojectfinal.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.it.myprojectfinal.R
import kotlinx.android.synthetic.main.activity_other.*

class OtherActivity : AppCompatActivity() {

    private val CALL = Manifest.permission.CALL_PHONE
    private val GRANTED = PackageManager.PERMISSION_GRANTED
    private val CALL_CODE = 999


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        btn_back_other.setOnClickListener {
            finish()
        }



        if (packageManager.hasSystemFeature(
                packageManager.toString()
            )
        ) {
            return

        }
        if (ContextCompat.checkSelfPermission(this,CALL)
            == GRANTED){
            call()
        }else{
            requestPermissions(arrayOf(CALL), CALL_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if (requestCode == CALL_CODE){
            if (permissions.isNotEmpty()&&
                grantResults[0]== GRANTED){
                call()
            }else{
                Toast.makeText(baseContext,"Permission Denied",Toast.LENGTH_LONG).show()
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    private  fun  call(){

         showcall.setOnClickListener{
        val itn = Intent(Intent.ACTION_DIAL)
        itn.setData(Uri.parse("tel:191"))
        startActivity(itn)
   }
         showcall1.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:199"))
            startActivity(itn)
        }
        showcall2.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1669"))
            startActivity(itn)
        }
        showcall3.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1155"))
            startActivity(itn)
        }
        showcall4.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1300"))
            startActivity(itn)
        }
        showcall5.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1192"))
            startActivity(itn)
        }
        showcall6.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1146"))
            startActivity(itn)
        }
        showcall7.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1808"))
            startActivity(itn)
        }
        showcall8.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1196"))
            startActivity(itn)
        }
        showcall9.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:1197"))
            startActivity(itn)
        }
        showcall10.setOnClickListener{
            val itn = Intent(Intent.ACTION_DIAL)
            itn.setData(Uri.parse("tel:192"))
            startActivity(itn)
        }

    }

}


