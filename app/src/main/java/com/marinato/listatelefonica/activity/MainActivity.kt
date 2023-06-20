package com.marinato.listatelefonica.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.marinato.listatelefonica.database.DBHelper
import com.marinato.listatelefonica.databinding.ActivityMainBinding
import com.marinato.listatelefonica.model.ContactModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactList: ArrayList<ContactModel>
    private lateinit var adapter: ArrayAdapter<ContactModel>
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
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

        binding.listViewContacts.setOnItemClickListener { _, _, position, _ ->
            /*Toast.makeText(applicationContext, contactList[position].name, Toast.LENGTH_SHORT).
            show() */
            val intent = Intent(applicationContext, ContactDetailActivity::class.java)
            intent.putExtra("id", contactList[position].id)
            startActivity(intent)
        }

        binding.buttonAdd.setOnClickListener{
            result.launch(Intent(applicationContext, NewContactActivity::class.java))
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.data != null && it.resultCode == 1){
                loadList()
            }else if (it.data != null && it.resultCode == 0){
                Toast.makeText(applicationContext, "Operation Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadList() {
        contactList = dbHelper.getAllContact()

        adapter =
            ArrayAdapter(
                applicationContext,
                android.R.layout.simple_list_item_1,
                contactList
            )
        binding.listViewContacts.adapter = adapter
    }
}
