package com.it.myprojectfinal.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.it.myprojectfinal.R

import com.it.myprojectfinal.model.response.DataList
import com.it.myprojectfinal.model.response.ResponseProfileBody
import com.it.myprojectfinal.view.main.DeleteMainActivity
import java.io.File


class AdapterDataNoti(

    private var context: Context,
    private var notiData: ArrayList<DataList>,
//    private var seeuser : ArrayList<ResponseProfileBody>,
//    private var mData : ArrayList<DeleteData>,
    private var mInvork: (String, String, String, String, String, String, String, String, String, String) -> (Unit)

) : RecyclerView.Adapter<AdapterDataNoti.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemdatanoti,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = notiData.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var flie = File("file:///C:/Users/Namnoy/Desktop/uploads/")
//        Glide.with(holder.itemView)
//            .load("https://www.photoschoolthailand.com/wp-content/uploads/2019/09/rule-of-third-1.jpg")
////            .load("$BaseUrl/uploads/${"Screenshot_2020-06-19-14-05-54.png"}")
////            .load(flie)
////            .placeholder(R.drawable.blue)
//            .into(holder.imagDetail)
        val bitmap = BitmapFactory.decodeFile(flie.absolutePath)
        holder.imagDetail.setImageBitmap(bitmap)
        holder.topic.text = notiData[position].notic_topic
        holder.detail.text = notiData[position].notic_detail
        holder.status.text = notiData[position].notic_status
        holder.time.text = notiData[position].notic_time

        holder.itemView.setOnClickListener {
            mInvork.invoke(

                notiData[position].notic_topic,
                notiData[position].notic_detail,
                notiData[position].notic_type,
                notiData[position].notic_voilent,
                notiData[position].notic_location,
                notiData[position].notic_status,
                notiData[position].notic_steps,
                notiData[position].notic_lat,
                notiData[position].notic_long,
                notiData[position].notic_time
            )
        }
        holder.itemView.setOnLongClickListener {

            val builderSingle = AlertDialog.Builder(context)
            val animals = arrayOf("ลบ")
            builderSingle.setItems(animals) { _, which ->
                when (which) {


                    0 // ลบข้อมูล
                    -> {
                        val i = Intent(context, DeleteMainActivity::class.java)
                        i.putExtra("id", notiData[position].notic_id)
                        context.startActivity(i)
                    }
                }
            }
            builderSingle.show()
            true

        }
    }


    class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {

        val topic: TextView = itemsView.findViewById<TextView>(R.id.TV_topic)
        val detail: TextView = itemsView.findViewById<TextView>(R.id.TV_detail)
        val status: TextView = itemsView.findViewById<TextView>(R.id.TV_status)
        val time: TextView = itemsView.findViewById<TextView>(R.id.TV_Time)
        val imagDetail: ImageView = itemsView.findViewById<ImageView>(R.id.ImView)


    }

    fun Updatedata(notiData: ArrayList<DataList>) {
        this.notiData = notiData
        notifyDataSetChanged()
    }

}