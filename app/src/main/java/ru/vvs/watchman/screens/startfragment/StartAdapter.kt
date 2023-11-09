package ru.vvs.watchman.screens.startfragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vvs.watchman.R
import ru.vvs.watchman.model.Journal

class StartAdapter: RecyclerView.Adapter<StartAdapter.StartViewHolder>() {

    private var listMain = emptyList<Journal>()
    //private var listMainFull = emptyList<Journal>()

    class StartViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_start_layout, parent, false)
        return StartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMain.size
    }

    override fun onBindViewHolder(holder: StartViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.item_date).text = listMain[position].dateRecord.substring(0 until 10)
        holder.itemView.findViewById<TextView>(R.id.item_time).text = listMain[position].dateRecord.substring(11..18)
        holder.itemView.findViewById<TextView>(R.id.item_message).text = listMain[position].messageRecord
        holder.itemView.findViewById<TextView>(R.id.item_type).text = listMain[position].typeRecord
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Journal>) {
        listMain = list
        //listMainFull = list
        notifyDataSetChanged()
    }

}