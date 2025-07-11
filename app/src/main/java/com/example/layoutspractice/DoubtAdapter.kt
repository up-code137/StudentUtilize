package com.example.layoutspractice

import Doubt
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DoubtAdapter(private val doubtList: List<Doubt>) : RecyclerView.Adapter<DoubtAdapter.DoubtViewHolder>() {

    class DoubtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectText: TextView = itemView.findViewById(R.id.subjectText)
        val doubtText: TextView = itemView.findViewById(R.id.doubtText)
        val responseText: TextView = itemView.findViewById(R.id.responseText) // 🆕 Added
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoubtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doubt, parent, false)
        return DoubtViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoubtViewHolder, position: Int) {
        val doubt = doubtList[position]
        holder.subjectText.text = doubt.subject
        holder.doubtText.text = doubt.doubt
        holder.responseText.text = if (doubt.aiAnswer.isNotEmpty()) doubt.aiAnswer else "⏳ Waiting for response..."
    }

    override fun getItemCount(): Int = doubtList.size
}
