package com.example.csc475portfolioproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class CharacterList: Fragment() {

    fun replace(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.character_container, fragment)
        fragmentTransaction.commit()

    }

    private fun getCharacters(view: View){
        //Load Data from shared preferences
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val characterNames = sharedPrefs.all

        //Create a scrolling list with RecyclerView
        val customAdapter = CharacterAdapter(characterNames)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvCharacters)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = customAdapter

        customAdapter.setOnButtonClickListener(object: CharacterAdapter.OnCharacterClickListener{
            override fun onButtonOpenClick(dataSetKey: String) {
                sharedPrefs.edit().remove(dataSetKey).apply()
                replace(fragment = CharacterList())

            }
            override fun onButtonEditClick(dataSetKey: String) {

                val bundle = Bundle()
                bundle.putString("characterName", dataSetKey)
                val fragment = AddCharacter()
                fragment.arguments = bundle
                replace(fragment)
                val faButton = activity?.findViewById<FloatingActionButton>(R.id.fbAddCharacter)
                faButton?.hide()
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_list, container,false)
        getCharacters(view)
        return view
    }
}