package com.example.csc475portfolioproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.csc475portfolioproject.EquipmentAdapter.OnEquipmentClickListener

class InventoryAdapter(private val dataSet: MutableMap<String, *>) :
    RecyclerView.Adapter<InventoryAdapter.ViewHolder>() {

    private var listener: OnInventoryClickListener? = null
    fun setOnButtonClickListener(listener: OnInventoryClickListener) {
        this.listener = listener
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Define click listener for the ViewHolder's View
        val tvItemDescription: TextView = view.findViewById(R.id.tvItemDescription)
        val tvItemName: TextView = view.findViewById(R.id.tvItemName)
        val inventoryRowView: View = view

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.inventory_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dataSetKeys = dataSet.keys.toTypedArray()
        val dataSetKey = dataSetKeys[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvItemDescription.text = dataSet[dataSetKey].toString()
        viewHolder.tvItemName.text = dataSetKey

        viewHolder.inventoryRowView.setOnClickListener {
            listener?.onButtonEditClick(dataSetKey)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnInventoryClickListener {
        fun onButtonEditClick(dataSetKey: String)
    }

}