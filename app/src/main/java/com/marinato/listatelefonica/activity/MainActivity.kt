package com.marinato.listatelefonica.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.marinato.listatelefonica.database.DBHelper
import com.marinato.listatelefonica.databinding.ActivityMainBinding
import com.marinato.listatelefonica.model.ContactModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactList: ArrayList<ContactModel>
    private lateinit var adapter: ArrayAdapter<ContactModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences(
            "login", Context.MODE_PRIVATE)

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        contactList = dbHelper.getAllContact()

        adapter =
            ArrayAdapter(
                applicationContext,
                android.R.layout.simple_list_item_1,
                contactList
            )
        binding.listViewContacts.adapter = adapter

        binding.listViewContacts.setOnItemClickListener { _, _, position, _ ->  }
            /*Toast.makeText(applicationContext, contactList[position].name, Toast.LENGTH_SHORT).
            show() */
        
    }
}
