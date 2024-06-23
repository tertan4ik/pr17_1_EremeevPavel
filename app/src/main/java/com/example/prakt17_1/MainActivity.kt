package com.example.prakt17_1

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var LoginText: EditText
    private lateinit var PassText: EditText
    private lateinit var Pref: SharedPreferences
    private lateinit var SaveLog: TextView
    private lateinit var SavePass: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoginText = findViewById(R.id.login)
        PassText = findViewById(R.id.password)
        SaveLog = findViewById(R.id.savelog)
        SavePass = findViewById(R.id.savepass)
    }

    fun handler(view: View)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.chose_a)).setMessage(getString(R.string.choose)).setCancelable(true)
            .setPositiveButton(getString(R.string.save_a))
            {
                    _, _ ->
                Pref = getPreferences(MODE_PRIVATE)
                val ed = Pref.edit()
                ed.putString("login", LoginText.text.toString())
                ed.putString("password", PassText.text.toString())
                ed.apply()
                SaveLog.text = (Pref.getString("login", ""))
                SavePass.text = (Pref.getString("password", ""))
                val alert = AlertDialog.Builder(this).setTitle(getString(R.string.good))
                    .setMessage(getString(R.string.save))
                    .setPositiveButton(getString(R.string.go), null).create().show()
            }
            .setNegativeButton(getString(R.string.load)) { _, _->
                Pref = getPreferences(MODE_PRIVATE)
                LoginText.setText(Pref.getString("login", ""))
                PassText.setText(Pref.getString("password", ""))
                val alert = AlertDialog.Builder(this).setTitle(R.string.good)
                    .setMessage(getString(R.string.load123))
                    .setPositiveButton(getString(R.string.go), null).create().show()
            }
        builder.create()
        builder.show()
    }
}