package com.example.rest_api_volley

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


class MainActivityPOST : AppCompatActivity() {
    lateinit var  code: EditText
    lateinit var  intitulé: EditText
    lateinit var  specialité: EditText
    lateinit var  société: EditText
    lateinit var  nbp: EditText
    lateinit var  pays: EditText
    lateinit var  ajout : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_post)

        code = findViewById(R.id.code)
        specialité = findViewById(R.id.specialité)
        intitulé = findViewById(R.id.intitulé)
        société = findViewById(R.id.société)
        nbp = findViewById(R.id.nbp)
        pays = findViewById(R.id.pays)
        ajout = findViewById(R.id.add)

        ajout.setOnClickListener {
            if (code.text.isEmpty() || specialité.text.isEmpty() || intitulé.text.isEmpty() || société.text.isEmpty() || nbp.text.isEmpty() || pays.text.isEmpty()) {
                Toast.makeText(this@MainActivityPOST, "Please enter values ", Toast.LENGTH_SHORT).show();

            }
            // calling a method to post the data and passing our name and job.
            postData(
                code.text.toString().toInt(),
                specialité.text.toString(),
                intitulé.text.toString(),
                société.text.toString(),
                nbp.text.toString().toInt(),
                pays.text.toString()
            );
        }
    }
        private fun postData(c : Int,sp : String,intu: String,sc : String,nb : Int,p : String ) {
            val postUrl = "http://192.168.43.35:8080/Offres"
            val requestQueue = Volley.newRequestQueue(this)

            val Data = JSONObject()
            try {
                Data.put("code", c)
                Data.put("specialité", sp)
                Data.put("intitulé", intu)
                Data.put("société", sc)
                Data.put("nbpostes", nb)
                Data.put("pays", p)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, postUrl, Data, {
                    Toast.makeText(this@MainActivityPOST, "add succesfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivityPOST, MainActivity::class.java)
                    startActivity(intent)
                }
            )
            { Toast.makeText(this@MainActivityPOST, "add failed", Toast.LENGTH_SHORT).show() }

            requestQueue.add(jsonObjectRequest)







        }}