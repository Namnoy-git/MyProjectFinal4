package com.it.myprojectfinal.ui.dashboard

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.it.myprojectfinal.R
import com.it.myprojectfinal.rest.local.Preferrences
import com.it.myprojectfinal.rest.local.Preferrences.Companion.FILENAME
import com.it.myprojectfinal.view.login.LoginActivity
import com.it.myprojectfinal.view.main.OtherActivity
import com.it.myprojectfinal.view.main.ProfileUserActivity
import kotlinx.android.synthetic.main.fragment_dashboard.view.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    lateinit var pref: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        pref = context?.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)!!


        
        view.hotline.setOnClickListener {
            val i = Intent(context, OtherActivity::class.java)
            startActivity(i)
        }

        view.profile.setOnClickListener {
            val m = Intent(context, ProfileUserActivity::class.java)
            startActivity(m)
        }
        view.logout.setOnClickListener {
            clearForLogout()
            val mIntent = Intent(context, LoginActivity::class.java)
            startActivity(mIntent)
        }

        return view
    }

    private fun clearForLogout() {
        val editor = pref.edit()
        editor.putString(Preferrences.TOKEN, "")
        editor.apply()
    }

}

