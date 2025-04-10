package com.example.csc475portfolioproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SpellsAdapter(private val dataSet: MutableMap<String, *>) :
    RecyclerView.Adapter<SpellsAdapter.ViewHolder>() {

    private var listener: OnSpellClickListener? = null
    fun setOnButtonClickListener(listener: OnSpellClickListener) {
        this.listener = listener
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        // Define click listener for the ViewHolder's View
        val tvSpellDescription: TextView = view.findViewById(R.id.tvSpellDescription)
        val tvSpellName: TextView = view.findViewById(R.id.tvSpellName)
        val spellRowView:View = view
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.spell_row, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val dataSetKeys = dataSet.keys.toTypedArray()
        val dataSetKey = dataSetKeys[position]
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvSpellDescription.text = dataSet[dataSetKey].toString()
        viewHolder.tvSpellName.text = dataSetKey

        viewHolder.spellRowView.setOnClickListener {
            listener?.onButtonEditClick(dataSetKey)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    interface OnSpellClickListener {
        fun onButtonEditClick(dataSetKey: String)
    }

}