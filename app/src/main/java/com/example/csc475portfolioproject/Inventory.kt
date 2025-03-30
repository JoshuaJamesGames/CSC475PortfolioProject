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

class Inventory : Fragment(){
    private fun getEquipment(view: View, characterName: String?){
        val prefName = characterName.plus("Equipment")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all
        val equipmentAdapter = EquipmentAdapter(dataset)


        val rcvEquippedItems: RecyclerView = view.findViewById(R.id.rcvEquippedItems)
        rcvEquippedItems.layoutManager = LinearLayoutManager(view.context)
        rcvEquippedItems.adapter = equipmentAdapter

        equipmentAdapter.setOnButtonClickListener(object: EquipmentAdapter.OnEquipmentClickListener{

            override fun onButtonEditClick(dataSetKey: String) {

                replace(loadFragment(AddNameAndDescription(), characterName, "Equipment", dataSetKey))

            }
        })
    }

    private fun getInventory(view: View, characterName: String?){
        val prefName = characterName.plus("Inventory")
        val sharedPrefs: SharedPreferences = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
        val dataset = sharedPrefs.all
        val inventoryAdapter = InventoryAdapter(dataset)

        val rcvInventory: RecyclerView = view.findViewById(R.id.rcvInventory)
        rcvInventory.layoutManager = LinearLayoutManager(view.context)
        rcvInventory.adapter = inventoryAdapter

        inventoryAdapter.setOnButtonClickListener(object: InventoryAdapter.OnInventoryClickListener{

            override fun onButtonEditClick(dataSetKey: String) {

                replace(loadFragment(AddNameAndDescription(), characterName, "Inventory", dataSetKey))

            }
        })

    }
    fun replace(fragment: Fragment) {

        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.character_container, fragment)
        fragmentTransaction.commit()

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val characterName = requireArguments().getString("characterName")
        val view = inflater.inflate(R.layout.inventory, container, false)

        val fabAddEquipment: FloatingActionButton = view.findViewById(R.id.fabAddEquippedItem)
        fabAddEquipment.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Equipment",null))
        }

        val fabAddInventory: FloatingActionButton = view.findViewById(R.id.fabAddInventory)
        fabAddInventory.setOnClickListener {
            replace(loadFragment(AddNameAndDescription(), characterName, "Inventory", null))
        }
        getEquipment(view, characterName)
        getInventory(view, characterName)

        return view
    }


}