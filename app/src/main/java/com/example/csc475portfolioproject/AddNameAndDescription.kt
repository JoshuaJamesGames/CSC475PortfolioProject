package com.example.csc475portfolioproject

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.core.content.edit

class AddNameAndDescription: Fragment() {

    fun replace(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.character_container, fragment)
        fragmentTransaction.commit()

    }

    //private val characterName = arguments?.getString("characterName")

    private fun loadFragment(fragment: Fragment, characterName: String?): Fragment{
        val newBundle = Bundle()
        newBundle.putString("characterName", characterName)
        val loadFragment: Fragment = fragment
        loadFragment.arguments = newBundle
        return loadFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_name_and_description, container, false)

        val btnSave :Button= view.findViewById(R.id.btnSave)
        val btnDelete :Button = view.findViewById(R.id.btnDelete)
        val tvTitle: TextView = view.findViewById(R.id.etTitle)
        val etAddName: EditText = view.findViewById(R.id.etAddName)
        val etAddDescription :EditText = view.findViewById(R.id.etAddDescription)

        tvTitle.text = arguments?.getString("addType")

        val characterName = requireArguments().getString("characterName")

        btnSave.setOnClickListener{
            val prefName = characterName.plus(tvTitle.text)
            val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
            if(etAddName.text.toString().isNotEmpty()){
                sharedPrefs.edit(commit = true) {
                    putString(
                        etAddName.text.toString(),
                        etAddDescription.text.toString()
                    )
                }
            }
            replace(loadFragment(AddCharacter(), characterName))
        }
        btnDelete.setOnClickListener{
            replace(loadFragment(AddCharacter(), characterName))
        }
        return view
    }

}