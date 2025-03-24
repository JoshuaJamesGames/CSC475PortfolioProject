package com.example.csc475portfolioproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Skills : Fragment(){
    private fun getSpells(view: View){
        val dataset = arrayOf("Fire Ball", "Death Touch", "Acid Arrow")
        val spellAdapter = SpellsAdapter(dataset)

        val rcvSpells: RecyclerView = view.findViewById(R.id.rcvSpells)
        rcvSpells.layoutManager = LinearLayoutManager(view.context)
        rcvSpells.adapter = spellAdapter
    }
    private fun getSkills(view: View){
        val dataset = arrayOf("Sneak", "Back Stab", "Pick Lock")
        val skillsAdapter = SkillsAdapter(dataset)

        val rcvSkills: RecyclerView = view.findViewById(R.id.rcvSkills)
        rcvSkills.layoutManager = LinearLayoutManager(view.context)
        rcvSkills.adapter = skillsAdapter
    }
    private fun getFeats(view: View){
        val dataset = arrayOf("DeadEye", "Mobile", "Fast Talker")
        val featAdapter = FeatAdapter(dataset)

        val rcvFeats: RecyclerView = view.findViewById(R.id.rcvFeats)
        rcvFeats.layoutManager = LinearLayoutManager(view.context)
        rcvFeats.adapter = featAdapter
    }
    private fun loadFragment(fragment: Fragment, characterName: String?, addType: String?): Fragment{
        val newBundle = Bundle()
        newBundle.putString("characterName", characterName)
        newBundle.putString("addType", addType)
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
            replace(loadFragment(AddNameAndDescription(), characterName, "Spell"))
        }
        val fabAddSkill: FloatingActionButton = view.findViewById(R.id.fabAddSkill)
        fabAddSkill.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Skill"))
        }
        val fabAddFeat: FloatingActionButton = view.findViewById(R.id.fabAddFeat)
        fabAddFeat.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Equipment"))
        }
        getSpells(view)
        getSkills(view)
        getFeats(view)

        return view
    }
}