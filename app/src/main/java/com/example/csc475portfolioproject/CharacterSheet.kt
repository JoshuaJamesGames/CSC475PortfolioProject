package com.example.csc475portfolioproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class CharacterSheet : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_sheet, container, false)

        if (arguments?.isEmpty == false) {
            val sharedPrefs: SharedPreferences =
                requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val characterName = requireArguments().getString("characterName")
            val character = sharedPrefs.getStringSet(characterName, null)
            val characterData =
                character.toString().substring(1, character.toString().length - 1).split(", ")

            fun getDataWithKey(key: String): String {
                var foundData: String = ""
                for ((index, value) in characterData.withIndex()) {

                    if (value.contains(key)) {
                        if (characterData[index].split(key).toString().length > 4) {
                            foundData = characterData[index].replace(key, "")
                        }
                    }
                }
                return foundData.toString()
            }

            view.findViewById<EditText>(R.id.etStrength).setText(getDataWithKey("Str: "))
            view.findViewById<EditText>(R.id.etDexterity).setText(getDataWithKey("Dex: "))
            view.findViewById<EditText>(R.id.etConstitution).setText(getDataWithKey("Con: "))
            view.findViewById<EditText>(R.id.etIntelligence).setText(getDataWithKey("Int: "))
            view.findViewById<EditText>(R.id.etWisdom).setText(getDataWithKey("Wis: "))
            view.findViewById<EditText>(R.id.etCharisma).setText(getDataWithKey("Cha: "))
            view.findViewById<EditText>(R.id.etArmorClass).setText(getDataWithKey("AC: "))
            view.findViewById<EditText>(R.id.etInitiative).setText(getDataWithKey("Init: "))
            view.findViewById<EditText>(R.id.etSpeed).setText(getDataWithKey("Speed: "))
            view.findViewById<EditText>(R.id.etHitDice).setText(getDataWithKey("HitDice: "))
            view.findViewById<EditText>(R.id.etMaxHP).setText(getDataWithKey("MaxHP: "))
            view.findViewById<EditText>(R.id.etCurrentHP).setText(getDataWithKey("CurrentHP: "))
            view.findViewById<EditText>(R.id.etTempHP).setText(getDataWithKey("TempHP: "))
        }
        return view

    }
}