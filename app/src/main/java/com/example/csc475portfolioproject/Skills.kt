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

class Skills : Fragment(){
    private fun getSpells(view: View, characterName: String?){
        val prefName = characterName.plus("Spell")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all
        val spellAdapter = SpellsAdapter(dataset)

        val rcvSpells: RecyclerView = view.findViewById(R.id.rcvSpells)
        rcvSpells.layoutManager = LinearLayoutManager(view.context)
        rcvSpells.adapter = spellAdapter

        spellAdapter.setOnButtonClickListener(object: SpellsAdapter.OnSpellClickListener{

            override fun onButtonEditClick(dataSetKey: String) {

                replace(loadFragment(AddNameAndDescription(), characterName, "Spell", dataSetKey))

            }
        })
    }
    private fun getSkills(view: View, characterName:String?){
        val prefName = characterName.plus("Skill")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all
        val skillsAdapter = SkillsAdapter(dataset)

        val rcvSkills: RecyclerView = view.findViewById(R.id.rcvSkills)
        rcvSkills.layoutManager = LinearLayoutManager(view.context)
        rcvSkills.adapter = skillsAdapter

        skillsAdapter.setOnButtonClickListener(object: SkillsAdapter.OnSkillClickListener{

            override fun onButtonEditClick(dataSetKey: String) {

                replace(loadFragment(AddNameAndDescription(), characterName, "Skill", dataSetKey))

            }
        })
    }
    private fun getFeats(view: View, characterName: String?){
        val prefName = characterName.plus("Feat")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all
        val featAdapter = FeatAdapter(dataset)

        val rcvFeats: RecyclerView = view.findViewById(R.id.rcvFeats)
        rcvFeats.layoutManager = LinearLayoutManager(view.context)
        rcvFeats.adapter = featAdapter

        featAdapter.setOnButtonClickListener(object: FeatAdapter.OnFeatClickListener{

            override fun onButtonEditClick(dataSetKey: String) {

                replace(loadFragment(AddNameAndDescription(), characterName, "Feat", dataSetKey))

            }
        })
    }
    private fun loadFragment(fragment: Fragment, characterName: String?, addType: String?, equipmentName: String?): Fragment{
        val newBundle = Bundle()
        newBundle.putString("characterName", characterName)
        newBundle.putString("addType", addType)
        newBundle.putString("equipmentName", equipmentName)
        val loadFragment: Fragment = fragment
        loadFragment.arguments = newBundle
        return loadFragment
    }
    fun replace(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.character_container, fragment)
        fragmentTransaction.commit()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.skills, container, false)
        val characterName = requireArguments().getString("characterName")

        val fabAddSpell: FloatingActionButton = view.findViewById(R.id.fabAddSpell)
        fabAddSpell.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Spell", null))
        }
        val fabAddSkill: FloatingActionButton = view.findViewById(R.id.fabAddSkill)
        fabAddSkill.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Skill", null))
        }
        val fabAddFeat: FloatingActionButton = view.findViewById(R.id.fabAddFeat)
        fabAddFeat.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Feat", null))
        }
        getSpells(view, characterName)
        getSkills(view, characterName)
        getFeats(view, characterName)

        return view
    }
}