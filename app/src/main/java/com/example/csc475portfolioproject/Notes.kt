package com.example.csc475portfolioproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Notes : Fragment(){

    private fun getNotes(view: View){
        val dataset = arrayOf("Here be Dragons", "A Deep Dungeon", "A Good Bar")
        val notesAdapter = NotesAdapter(dataset)

        val rcvNotes: RecyclerView = view.findViewById(R.id.rcvNotes)
        rcvNotes.layoutManager = LinearLayoutManager(view.context)
        rcvNotes.adapter = notesAdapter
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
        val view = inflater.inflate(R.layout.notes, container, false)

        val characterName = requireArguments().getString("characterName")

        val fabAddNote: FloatingActionButton = view.findViewById(R.id.fabAddNote)
        fabAddNote.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Note"))
        }
        getNotes(view)

        return view
    }
}