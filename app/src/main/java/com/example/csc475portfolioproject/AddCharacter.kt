package com.example.csc475portfolioproject

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import androidx.core.content.edit


class AddCharacter : Fragment() {

    private fun saveCharacter(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.character_container, fragment)
        fragmentTransaction.commit()

        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        sharedPrefs.edit(commit = true) {

            val newCharacterName = activity?.findViewById<EditText>(R.id.etName)?.text.toString()
            val newCharacterClass = activity?.findViewById<EditText>(R.id.etClass)?.text.toString()
            val newCharacterLevel = activity?.findViewById<EditText>(R.id.etLevel)?.text.toString()
            val newCharacterRace = activity?.findViewById<EditText>(R.id.etRace)?.text.toString()
            val newCharacterBackground =
                activity?.findViewById<EditText>(R.id.etBackground)?.text.toString()
            //Stats
            val newCharacterStr = activity?.findViewById<EditText>(R.id.etStrength)?.text.toString()
            val newCharacterDex = activity?.findViewById<EditText>(R.id.etDexterity)?.text.toString()
            val newCharacterCon = activity?.findViewById<EditText>(R.id.etConstitution)?.text.toString()
            val newCharacterInt = activity?.findViewById<EditText>(R.id.etIntelligence)?.text.toString()
            val newCharacterWis = activity?.findViewById<EditText>(R.id.etWisdom)?.text.toString()
            val newCharacterCha = activity?.findViewById<EditText>(R.id.etCharisma)?.text.toString()
            //Battle
            val newCharacterAC = activity?.findViewById<EditText>(R.id.etArmorClass)?.text.toString()
            val newCharacterInit = activity?.findViewById<EditText>(R.id.etInitiative)?.text.toString()
            val newCharacterSpeed = activity?.findViewById<EditText>(R.id.etSpeed)?.text.toString()
            val newCharacterHitDice = activity?.findViewById<EditText>(R.id.etHitDice)?.text.toString()
            //HP
            val newCharacterMaxHP = activity?.findViewById<EditText>(R.id.etMaxHP)?.text.toString()
            val newCharacterCurrentHP = activity?.findViewById<EditText>(R.id.etCurrentHP)?.text.toString()
            val newCharacterTempHP = activity?.findViewById<EditText>(R.id.etTempHP)?.text.toString()

            if (newCharacterName.isNotEmpty()) {
                putStringSet(
                    newCharacterName,
                    setOf(
                        "Background: $newCharacterBackground",
                        "Class: $newCharacterClass",
                        "Level: $newCharacterLevel",
                        "Race: $newCharacterRace",
                        "Str: $newCharacterStr",
                        "Dex: $newCharacterDex",
                        "Con: $newCharacterCon",
                        "Int: $newCharacterInt",
                        "Wis: $newCharacterWis",
                        "Cha: $newCharacterCha",
                        "AC: $newCharacterAC",
                        "Init: $newCharacterInit",
                        "Speed: $newCharacterSpeed",
                        "HitDice: $newCharacterHitDice",
                        "MaxHP: $newCharacterMaxHP",
                        "CurrentHP: $newCharacterCurrentHP",
                        "TempHP: $newCharacterTempHP",
                    )
                )
            }
        }

        activity?.findViewById<FloatingActionButton>(R.id.fbAddCharacter)?.show()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.add_character, container,false)
        val btnAddContact = view.findViewById<Button>(R.id.btnAddCharacter)

        if(arguments?.isEmpty == false){
            val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val characterName = requireArguments().getString("characterName")
            val character = sharedPrefs.getStringSet(characterName,null)
            val characterData = character.toString().substring(1,character.toString().length-1).split(", ")

            fun getDataWithKey(key: String): String {
                var foundData: String? = null
                for ((index, value) in characterData.withIndex()) {

                    if (value.contains(key)) {
                        if(characterData[index].split(key).toString().length >4 ) {
                            foundData = characterData[index].replace(key, "")
                        }
                    }
                }
                return foundData.toString()
            }



            view.findViewById<EditText>(R.id.etName).setText(requireArguments().getString("characterName"))
            view.findViewById<EditText>(R.id.etClass).setText(getDataWithKey("Class: "))
            view.findViewById<EditText>(R.id.etLevel).setText(getDataWithKey("Level: "))
            view.findViewById<EditText>(R.id.etRace).setText(getDataWithKey("Race: "))
            view.findViewById<EditText>(R.id.etBackground).setText(getDataWithKey("Background: "))

        }
        btnAddContact.setOnClickListener {
            saveCharacter(fragment = CharacterList())
        }

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager2 = view.findViewById<ViewPager2>(R.id.viewPager2)
        val bundle = Bundle()
        bundle.putString("characterName", requireArguments().getString("characterName") )
        val adapter = CharacterViewPagerAdapter(parentFragmentManager, lifecycle, bundle)
        viewPager2.adapter = adapter

        val tabNamesArray = arrayOf("Stats", "Proficiencies", "Inventory", "Skills", "Notes")
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabNamesArray[position]
        }.attach()

        return view
    }



}