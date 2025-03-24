package com.example.csc475portfolioproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EquipmentAdapter(private val dataSet: MutableMap<String, *>) :
    RecyclerView.Adapter<EquipmentAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            // Define click listener for the ViewHolder's View
            val tvEquipedItemDescription: TextView = view.findViewById(R.id.tvEquipmentDescription)
            val tvEquipmentName: TextView = view.findViewById(R.id.tvEquipmentName)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.equipment_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dataSetKeys = dataSet.keys.toTypedArray()
        val dataSetKey = dataSetKeys[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvEquipedItemDescription.text = dataSet[dataSetKey].toString()
        viewHolder.tvEquipmentName.text = dataSetKey
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}