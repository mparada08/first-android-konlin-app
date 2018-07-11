package pl.net.parada.dietplus.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item.view.*
import pl.net.parada.dietplus.R

class RecyclerViewAdapter(private val items: ArrayList<Pair<String, String>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int): RecyclerView.ViewHolder {
        val li = LayoutInflater.from(p0?.context).inflate(R.layout.list_item, p0, false)
        return object : RecyclerView.ViewHolder(li) {}
    }

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        p0.itemView.header.text = items[p1].first
        p0.itemView.descriptionText.text = items[p1].second
    }


}