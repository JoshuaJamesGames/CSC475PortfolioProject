package com.example.csc475portfolioproject

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import androidx.fragment.app.Fragment

class Proficiencies : Fragment(){

    private fun makeCheckable(radioButton: RadioButton){

        radioButton.setOnClickListener{
            Log.i(TAG,radioButton.isChecked.toString())
            radioButton.isChecked = !radioButton.isChecked


        }
    }

    private fun setProficiencies(view: View){
        val rbAcrobatics = view.findViewById<RadioButton>(R.id.rbAcrobatics)
        rbAcrobatics.isChecked = true
        makeCheckable(rbAcrobatics)
        val rbExpertAcrobatics = view.findViewById<RadioButton>(R.id.rbExpertAcrobatics)
        makeCheckable(rbExpertAcrobatics)
        val rbAnimalHandling = view.findViewById<RadioButton>(R.id.rbAnimalHandling)
        makeCheckable(rbAnimalHandling)
        val rbExpertAnimalHandling = view.findViewById<RadioButton>(R.id.rbExpertAnimalHandling)
        makeCheckable(rbExpertAnimalHandling)
        val rbArcana = view.findViewById<RadioButton>(R.id.rbArcana)
        makeCheckable(rbArcana)
        val rbExpertArcana = view.findViewById<RadioButton>(R.id.rbExpertArcana)
        makeCheckable(rbExpertArcana)
        val rbAthletics = view.findViewById<RadioButton>(R.id.rbAthletics)
        makeCheckable(rbAthletics)
        val rbExpertAthletics = view.findViewById<RadioButton>(R.id.rbExpertAthletics)
        makeCheckable(rbExpertAthletics)
        val rbDeception = view.findViewById<RadioButton>(R.id.rbDeception)
        makeCheckable(rbDeception)
        val rbExpertDeception = view.findViewById<RadioButton>(R.id.rbExpertDeception)
        makeCheckable(rbExpertDeception)
        val rbHistory = view.findViewById<RadioButton>(R.id.rbHistory)
        makeCheckable(rbHistory)
        val rbExpertHistory = view.findViewById<RadioButton>(R.id.rbExpertHistory)
        makeCheckable(rbExpertHistory)
        val rbInsight = view.findViewById<RadioButton>(R.id.rbInsight)
        makeCheckable(rbInsight)
        val rbExpertInsight = view.findViewById<RadioButton>(R.id.rbExpertInsight)
        makeCheckable(rbExpertInsight)
        val rbIntimidation = view.findViewById<RadioButton>(R.id.rbIntimidation)
        makeCheckable(rbIntimidation)
        val rbExpertIntimidation = view.findViewById<RadioButton>(R.id.rbExpertIntimidation)
        makeCheckable(rbExpertIntimidation)
        val rbInvestigation = view.findViewById<RadioButton>(R.id.rbInvestigation)
        makeCheckable(rbInvestigation)
        val rbExpertInvestigation = view.findViewById<RadioButton>(R.id.rbExpertInvestigation)
        makeCheckable(rbExpertInvestigation)
        val rbMedicine = view.findViewById<RadioButton>(R.id.rbMedicine)
        makeCheckable(rbMedicine)
        val rbExpertMedicine = view.findViewById<RadioButton>(R.id.rbExpertMedicine)
        makeCheckable(rbExpertMedicine)
        val rbNature = view.findViewById<RadioButton>(R.id.rbNature)
        makeCheckable(rbNature)
        val rbExpertNature = view.findViewById<RadioButton>(R.id.rbExpertNature)
        makeCheckable(rbExpertNature)
        val rbPerception = view.findViewById<RadioButton>(R.id.rbPerception)
        makeCheckable(rbPerception)
        val rbExpertPerception = view.findViewById<RadioButton>(R.id.rbExpertPerception)
        makeCheckable(rbExpertPerception)
        val rbPerformance = view.findViewById<RadioButton>(R.id.rbPerformance)
        makeCheckable(rbPerformance)
        val rbExpertPerformance = view.findViewById<RadioButton>(R.id.rbExpertPerformance)
        makeCheckable(rbExpertPerformance)
        val rbPersuasion = view.findViewById<RadioButton>(R.id.rbPersuasion)
        makeCheckable(rbPersuasion)
        val rbExpertPersuasion = view.findViewById<RadioButton>(R.id.rbExpertPersuasion)
        makeCheckable(rbExpertPersuasion)
        val rbReligion = view.findViewById<RadioButton>(R.id.rbReligion)
        makeCheckable(rbReligion)
        val rbExpertReligion = view.findViewById<RadioButton>(R.id.rbExpertReligion)
        makeCheckable(rbExpertReligion)
        val rbSleightOfHand = view.findViewById<RadioButton>(R.id.rbSleightOfHand)
        makeCheckable(rbSleightOfHand)
        val rbExpertSleightOfHand = view.findViewById<RadioButton>(R.id.rbExpertSleightOfHand)
        makeCheckable(rbExpertSleightOfHand)
        val rbStealth = view.findViewById<RadioButton>(R.id.rbStealth)
        makeCheckable(rbStealth)
        val rbExpertStealth = view.findViewById<RadioButton>(R.id.rbExpertStealth)
        makeCheckable(rbExpertStealth)
        val rbSurvival = view.findViewById<RadioButton>(R.id.rbSurvival)
        makeCheckable(rbSurvival)
        val rbExpertSurvival = view.findViewById<RadioButton>(R.id.rbExpertSurvival)
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

            setProficiencies(view)

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