package com.example.csc475portfolioproject

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.content.edit
import androidx.fragment.app.Fragment

class Proficiencies : Fragment(){



    private fun setProficiencies(view: View, characterName: String?){
        val prefName = characterName.plus("Proficiencies")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all

        fun makeCheckable(checkBox: CheckBox){
            if(dataset[checkBox.id.toString()] !=null){
                if(dataset[checkBox.id.toString()] as Boolean){
                    checkBox.isChecked = true
                }

            }
            checkBox.setOnClickListener{

                if(checkBox.isChecked) {
                    sharedPrefs.edit(commit = true) {
                        putBoolean(checkBox.id.toString(), true)
                    }
                }else{
                    sharedPrefs.edit(commit = true) {
                        putBoolean(checkBox.id.toString(), false)
                    }
                }
            }

        }


        val rbAcrobatics = view.findViewById<CheckBox>(R.id.rbAcrobatics)
        makeCheckable(rbAcrobatics)
        val rbExpertAcrobatics = view.findViewById<CheckBox>(R.id.rbExpertAcrobatics)
        makeCheckable(rbExpertAcrobatics)
        val rbAnimalHandling = view.findViewById<CheckBox>(R.id.rbAnimalHandling)
        makeCheckable(rbAnimalHandling)
        val rbExpertAnimalHandling = view.findViewById<CheckBox>(R.id.rbExpertAnimalHandling)
        makeCheckable(rbExpertAnimalHandling)
        val rbArcana = view.findViewById<CheckBox>(R.id.rbArcana)
        makeCheckable(rbArcana)
        val rbExpertArcana = view.findViewById<CheckBox>(R.id.rbExpertArcana)
        makeCheckable(rbExpertArcana)
        val rbAthletics = view.findViewById<CheckBox>(R.id.rbAthletics)
        makeCheckable(rbAthletics)
        val rbExpertAthletics = view.findViewById<CheckBox>(R.id.rbExpertAthletics)
        makeCheckable(rbExpertAthletics)
        val rbDeception = view.findViewById<CheckBox>(R.id.rbDeception)
        makeCheckable(rbDeception)
        val rbExpertDeception = view.findViewById<CheckBox>(R.id.rbExpertDeception)
        makeCheckable(rbExpertDeception)
        val rbHistory = view.findViewById<CheckBox>(R.id.rbHistory)
        makeCheckable(rbHistory)
        val rbExpertHistory = view.findViewById<CheckBox>(R.id.rbExpertHistory)
        makeCheckable(rbExpertHistory)
        val rbInsight = view.findViewById<CheckBox>(R.id.rbInsight)
        makeCheckable(rbInsight)
        val rbExpertInsight = view.findViewById<CheckBox>(R.id.rbExpertInsight)
        makeCheckable(rbExpertInsight)
        val rbIntimidation = view.findViewById<CheckBox>(R.id.rbIntimidation)
        makeCheckable(rbIntimidation)
        val rbExpertIntimidation = view.findViewById<CheckBox>(R.id.rbExpertIntimidation)
        makeCheckable(rbExpertIntimidation)
        val rbInvestigation = view.findViewById<CheckBox>(R.id.rbInvestigation)
        makeCheckable(rbInvestigation)
        val rbExpertInvestigation = view.findViewById<CheckBox>(R.id.rbExpertInvestigation)
        makeCheckable(rbExpertInvestigation)
        val rbMedicine = view.findViewById<CheckBox>(R.id.rbMedicine)
        makeCheckable(rbMedicine)
        val rbExpertMedicine = view.findViewById<CheckBox>(R.id.rbExpertMedicine)
        makeCheckable(rbExpertMedicine)
        val rbNature = view.findViewById<CheckBox>(R.id.rbNature)
        makeCheckable(rbNature)
        val rbExpertNature = view.findViewById<CheckBox>(R.id.rbExpertNature)
        makeCheckable(rbExpertNature)
        val rbPerception = view.findViewById<CheckBox>(R.id.rbPerception)
        makeCheckable(rbPerception)
        val rbExpertPerception = view.findViewById<CheckBox>(R.id.rbExpertPerception)
        makeCheckable(rbExpertPerception)
        val rbPerformance = view.findViewById<CheckBox>(R.id.rbPerformance)
        makeCheckable(rbPerformance)
        val rbExpertPerformance = view.findViewById<CheckBox>(R.id.rbExpertPerformance)
        makeCheckable(rbExpertPerformance)
        val rbPersuasion = view.findViewById<CheckBox>(R.id.rbPersuasion)
        makeCheckable(rbPersuasion)
        val rbExpertPersuasion = view.findViewById<CheckBox>(R.id.rbExpertPersuasion)
        makeCheckable(rbExpertPersuasion)
        val rbReligion = view.findViewById<CheckBox>(R.id.rbReligion)
        makeCheckable(rbReligion)
        val rbExpertReligion = view.findViewById<CheckBox>(R.id.rbExpertReligion)
        makeCheckable(rbExpertReligion)
        val rbSleightOfHand = view.findViewById<CheckBox>(R.id.rbSleightOfHand)
        makeCheckable(rbSleightOfHand)
        val rbExpertSleightOfHand = view.findViewById<CheckBox>(R.id.rbExpertSleightOfHand)
        makeCheckable(rbExpertSleightOfHand)
        val rbStealth = view.findViewById<CheckBox>(R.id.rbStealth)
        makeCheckable(rbStealth)
        val rbExpertStealth = view.findViewById<CheckBox>(R.id.rbExpertStealth)
        makeCheckable(rbExpertStealth)
        val rbSurvival = view.findViewById<CheckBox>(R.id.rbSurvival)
        makeCheckable(rbSurvival)
        val rbExpertSurvival = view.findViewById<CheckBox>(R.id.rbExpertSurvival)
        makeCheckable(rbExpertSurvival)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.proficiencies, container, false)

        if (arguments?.isEmpty == false) {
            val sharedPrefs: SharedPreferences =
                requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val characterName = requireArguments().getString("characterName")
            val character = sharedPrefs.getStringSet(characterName, null)
            val characterData =
                character.toString().substring(1, character.toString().length - 1).split(", ")

            fun getDataWithKey(key: String): String {
                var foundData: String? = null
                for ((index, value) in characterData.withIndex()) {

                    if (value.contains(key)) {
                        if (characterData[index].split(key).toString().length > 4) {
                            foundData = characterData[index].replace(key, "")
                        }
                    }
                }
                return foundData.toString()
            }

            setProficiencies(view, characterName)

            view.findViewById<EditText>(R.id.etStrength).setText(getDataWithKey("Str: "))
            view.findViewById<EditText>(R.id.etDexterity).setText(getDataWithKey("Dex: "))
            view.findViewById<EditText>(R.id.etConstitution).setText(getDataWithKey("Con: "))
            view.findViewById<EditText>(R.id.etIntelligence).setText(getDataWithKey("Int: "))
            view.findViewById<EditText>(R.id.etWisdom).setText(getDataWithKey("Wis: "))
            view.findViewById<EditText>(R.id.etCharisma).setText(getDataWithKey("Cha: "))

        }
        return view

    }
}