package com.marinato.listatelefonica.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.marinato.listatelefonica.model.ContactModel
import com.marinato.listatelefonica.model.UserModel
import java.net.Inet4Address

class DBHelper(context: Context) : SQLiteOpenHelper (context, "database.db", null, 1){

    private val sql = arrayOf(
        "CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT UNIQUE, password TEXT)",
        "INSERT INTO users (username, password) VALUES ('admin', 'password'0)",
        "CREATE TABLE contact(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, " +
                "email TEXT, address TEXT, phone INT, imageId TEXT)",
        "INSERT INTO contact (name, address, email, phone, imageId) VALUES " +
                "('Paulo', 'address Paulo', 'paulo@email.com', '911112222',1)",
        "INSERT INTO contact (name, address, email, phone, imageId) VALUES " +
                "(Roberto, 'address Roberto', Roberto@email.com, 933334444,2)",
        "INSERT INTO contact (name, address, email, phone, imageId) VALUES " +
                "(Marinato, 'address Marinato', marinato@email.com, 955556666,3)",
    )

    override fun onCreate(db: SQLiteDatabase?) {
        sql.forEach {
            db?.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
                            /* --- CRUD USER --- */

    fun insertuser(username: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val res = db.insert("users", null, contentValues)
        db.close()
        return res
    }

    fun updateUser(id: Int, username: String, password: String): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("username", username)
        contentValues.put("password", password)
        val res = db.update("users", contentValues, "id=?",
            arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteUser(id: Int): Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        val res = db.delete("users","id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun getuser(username: String, password: String): UserModel{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?",
            arrayOf(username, password)
        )
         var userModel  = UserModel()

        if (c.count == 1){
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val usernameIndex = c.getColumnIndex("username")
            val passwordIndex = c.getColumnIndex("password")

            userModel = UserModel(
                id = c.getInt(idIndex),
                username = c.getString(usernameIndex),
                password = c.getString(passwordIndex))
        }

        db.close()
        return userModel
    }
    fun login(username: String, password: String): Boolean{
        val db = this.readableDatabase
        val c = db.rawQuery(
            "SELECT * FROM users WHERE username=? AND password=?",
            arrayOf(username, password))

        var userModel  = UserModel()

        return if (c.count == 1){
            db.close()
            true
        }else {
            db.close()
            false
        }
    }
                                    /* --- CRUD CONTACTS --- */

    fun insertContact(name: String, address: String, email: String, phone: Int, ImageId: Int): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        contentValues.put("imageId", ImageId)
        val res = db.insert("contacts", null, contentValues)
        db.close()
        return res
    }

    fun updateContact(id: Int, name: String, address: String, email: String, phone: Int, ImageId: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("address", address)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        contentValues.put("imageId", ImageId)
        val res = db.update("contact", contentValues, "id=?",
            arrayOf(id.toString()))
        db.close()
        return res
    }

    fun deleteContact(id: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        val res = db.delete("contact","id=?", arrayOf(id.toString()))
        db.close()
        return res
    }

    fun getContact(id: Int): ContactModel{
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM contact WHERE id=?",
            arrayOf(id.toString())
        )
        var contactModel = ContactModel()

        if (c.count == 1){
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val emailIndex = c.getColumnIndex("email")
            val phoneIndex = c.getColumnIndex("phone")
            val imageIdIndex = c.getColumnIndex("imageId")

            contactModel = ContactModel(
                id = c.getInt(idIndex),
                name = c.getString(nameIndex),
                email = c.getString(emailIndex),
                phone = c.getInt(phoneIndex),
                imageId = c.getInt(imageIdIndex)
            )
        }

        db.close()
        return contactModel
    }

    fun getAllContact(): ArrayList<ContactModel> {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM contact",null)
               var listContactModel = ArrayList<ContactModel>()

        if (c.count > 0) {
            c.moveToFirst()
            val idIndex = c.getColumnIndex("id")
            val nameIndex = c.getColumnIndex("name")
            val emailIndex = c.getColumnIndex("email")
            val phoneIndex = c.getColumnIndex("phone")
            val imageIdIndex = c.getColumnIndex("imageId")
            do {
                val contactModel = ContactModel(
                    id = c.getInt(idIndex),
                    name = c.getString(nameIndex),
                    email = c.getString(emailIndex),
                    phone = c.getInt(phoneIndex),
                    imageId = c.getInt(imageIdIndex))

                listContactModel.add(contactModel)
            }while (c.moveToNext())
        }
        db.close()
        return listContactModel
    }
}