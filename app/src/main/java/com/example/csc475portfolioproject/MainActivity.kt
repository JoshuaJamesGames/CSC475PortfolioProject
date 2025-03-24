package com.example.csc475portfolioproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var db :AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Using Fragments to change the UI so only one Activity
        fun replace(fragment: Fragment) {

            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.character_container, fragment)
            fragmentTransaction.commit()

        }

        //Click the Plus to add a Character
        val faButton = findViewById<FloatingActionButton>(R.id.fbAddCharacter)
        faButton.setOnClickListener {
            replace(fragment = AddCharacter())
            faButton.hide()
        }
        //My attempts to get Room Database working failed
        //db = Room.databaseBuilder(applicationContext,AppDatabase::class.java,"my_characters").build()
        //val samplechar = Character(characterName = "Trogun", characterClass = "Warrior", characterLevel = 1)

        //Start by loading the Characters
        replace(fragment = CharacterList())
    }
}