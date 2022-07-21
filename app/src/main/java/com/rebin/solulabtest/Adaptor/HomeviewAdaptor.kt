package com.rebin.solulabtest.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rebin.solulabtest.R
import com.rebin.solulabtest.network.Response
import com.squareup.picasso.Picasso

class HomeviewAdaptor(var list: List<Response>) : RecyclerView.Adapter<HomeviewAdaptor.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvtext: TextView
        var ivimg: ImageView
        init {
            tvtext=view.findViewById(R.id.tvname)
            ivimg=view.findViewById(R.id.ivcoin)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvtext.text=list.get(position).name
        if(list.get(position).pictures!=null){
            Picasso.get()
                .load(list.get(position).pictures.front.url)
                .into(holder.ivimg)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
