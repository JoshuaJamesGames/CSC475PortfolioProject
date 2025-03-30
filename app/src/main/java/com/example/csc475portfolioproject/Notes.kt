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

class Notes : Fragment(){

    private fun getNotes(view: View, characterName: String?){
        val prefName = characterName.plus("Note")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all
        val notesAdapter = NotesAdapter(dataset)

        val rcvNotes: RecyclerView = view.findViewById(R.id.rcvNotes)
        rcvNotes.layoutManager = LinearLayoutManager(view.context)
        rcvNotes.adapter = notesAdapter

        notesAdapter.setOnButtonClickListener(object: NotesAdapter.OnNoteClickListener{

            override fun onButtonEditClick(dataSetKey: String) {

                replace(loadFragment(AddNameAndDescription(), characterName, "Note", dataSetKey))

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
        val view = inflater.inflate(R.layout.notes, container, false)

        val characterName = requireArguments().getString("characterName")

        val fabAddNote: FloatingActionButton = view.findViewById(R.id.fabAddNote)
        fabAddNote.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Note", null))
        }
        getNotes(view, characterName)

        return view
    }
}