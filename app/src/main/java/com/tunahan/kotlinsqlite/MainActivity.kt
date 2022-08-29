package com.tunahan.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.tunahan.kotlinsqlite.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val v = binding.root
        setContentView(v)

        try {
            val myDatabase = this.openOrCreateDatabase("Animal", MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS animal (id INTEGER PRIMARY KEY,name VARCHAR, age INT)")

            myDatabase.execSQL("INSERT INTO animal(name,age) VALUES ('dog',12)")
            myDatabase.execSQL("INSERT INTO animal(name,age) VALUES ('cat',15)")
            //UPDATE -> güncelle
            myDatabase.execSQL("UPDATE animal SET age = 14 WHERE name = 'dog'")
            //DELETE -> veri silme
            myDatabase.execSQL("DELETE FROM animal WHERE name = 'cat'")

            //WHERE -> şu olduğunda , WHERE id = 2 -> id 2 olanları getir
            // WHERE name LIKE '%a' -> sonu a ile bitenleri getir
            //val cursor = myDatabase.rawQuery("SELECT * FROM animal WHERE name LIKE 'd%' ",null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM animal WHERE id = 2",null)

            val cursor = myDatabase.rawQuery("SELECT * FROM animal",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            while (cursor.moveToNext()){
                println("name : " + cursor.getString(nameIx))
                println("age : " + cursor.getInt(ageIx))
                println("id : " + cursor.getInt(idIx))

            }
            cursor.close()


        }catch (e: Exception){
            e.printStackTrace()
        }

    }
}