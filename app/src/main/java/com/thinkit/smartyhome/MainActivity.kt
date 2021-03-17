package com.thinkit.smartyhome

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   private var homeIntent: Intent? = null
       var sharedPref:SharedPreferences? = null
    companion object{
        const val  USER_NAME_KEY : String = "userName"
        const val  SHARED_PREF_KEY : String = "myPrefs"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPref =  getSharedPreferences(SHARED_PREF_KEY,Context.MODE_PRIVATE)
        val isUserSaved = sharedPref!!.getString(USER_NAME_KEY,null) != null
        if(isUserSaved){
            goToHome()
            finish()
        }
        continueBtn.setOnClickListener(View.OnClickListener {
            btn -> getUserName()

        })
    }

    fun goToHome(){
        if(homeIntent == null){
            homeIntent = Intent(this,HomeActivity::class.java)
        }
        startActivity(homeIntent)
    }

    fun getUserName(){

            val name = nameEditText.text.toString()
            val isValid = name.matches("^[A-Za-z]*$".toRegex())
            if (!isValid) {
                Toast.makeText(this, "Enter a valid name !!",Toast.LENGTH_LONG).show()
            }
            if( name != "" && isValid){
                    sharedPref!!.edit().putString(USER_NAME_KEY,name).apply()
                goToHome()
            }else{
                Toast.makeText(this, "Enter your name !!",Toast.LENGTH_LONG).show()
            }

    }
}