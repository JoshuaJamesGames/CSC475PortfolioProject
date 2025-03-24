package com.example.csc475portfolioproject

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CharacterAdapter(private val dataSet: MutableMap<String, *>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    private var listener: OnCharacterClickListener? = null
    fun setOnButtonClickListener(listener: OnCharacterClickListener) {
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvCharacterName: TextView = view.findViewById(R.id.tvCharacterName)
        val tvCharacterClass: TextView = view.findViewById(R.id.tvCharacterClass)
        val tvCharacterLevel: TextView = view.findViewById(R.id.tvCharacterLevel)

        val btnEditCharacter: Button = view.findViewById(R.id.btnEditCharacter)
        val btnOpenCharacter: Button = view.findViewById(R.id.btnOpenCharacter)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.character_row, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val dataSetKeys = dataSet.keys.toTypedArray()
        val dataSetKey = dataSetKeys[position]
        val contactValues =
            dataSet[dataSetKey].toString().substring(1, dataSet[dataSetKey].toString().length - 1)
                .split(", ").sorted()

        Log.i(TAG, contactValues.toString())
        fun getDataWithKey(key: String): String {
            var foundData: String? =null
            for ((index, value) in contactValues.withIndex()) {

                if (value.contains(key)) {
                    if(contactValues[index].split(key).toString().length >4 ) {
                        foundData = contactValues[index].replace(key, "")

                    }
                }
            }
            return foundData.toString()
        }

        val characterClass = getDataWithKey("Class: ")
        val characterLevel = getDataWithKey("Level: ")

        viewHolder.tvCharacterName.text = dataSetKey
        viewHolder.tvCharacterClass.text = characterClass
        viewHolder.tvCharacterLevel.text = characterLevel


        viewHolder.btnOpenCharacter.setOnClickListener {
            listener?.onButtonOpenClick(dataSetKey)
        }
        viewHolder.btnEditCharacter.setOnClickListener {
            listener?.onButtonEditClick(dataSetKey)
        }


    }

    override fun getItemCount() = dataSet.size

    interface OnCharacterClickListener {
        fun onButtonOpenClick(dataSetKey: String)
        fun onButtonEditClick(dataSetKey: String)
    }
}