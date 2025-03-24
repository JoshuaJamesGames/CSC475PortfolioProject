package com.example.csc475portfolioproject


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

private const val NUM_TABS = 5

class CharacterViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    bundle: Bundle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {



    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {


        when (position) {

            1 -> {
                return loadFragment(Proficiencies())
            }
            2 -> {
                return loadFragment(Inventory())
            }
            3 -> {
                return loadFragment(Skills())
            }
            4 -> {
                return loadFragment(Notes())
            }
        }
        return loadFragment(CharacterSheet())
    }

    private val characterName = bundle.getString("characterName")
    //Add the Bundle of characterName to propagate to fragments
    private fun loadFragment(fragment: Fragment): Fragment{
        val newBundle = Bundle()
        newBundle.putString("characterName", characterName)
        val loadFragment: Fragment = fragment
        loadFragment.arguments = newBundle
        return loadFragment
    }
}