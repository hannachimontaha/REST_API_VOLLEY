package com.example.rest_api_volley

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivityPUT : AppCompatActivity() {
    lateinit var a: EditText
    lateinit var b: EditText
    lateinit var c: EditText
    lateinit var d: EditText
    lateinit var e: EditText
    lateinit var f: EditText
    lateinit var modifier: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_put)

        a = findViewById(R.id.a)
        b = findViewById(R.id.b)
        c = findViewById(R.id.c)
        d = findViewById(R.id.d)
        e = findViewById(R.id.e)
        f = findViewById(R.id.f)

        val extras = intent.extras
        val val1 = extras!!.getInt("code").toString()
        val id = extras!!.getInt("code")
        val val2 = extras!!.getString("intitule")
        val val3 = extras!!.getString("specialite")
        val val4 = extras!!.getString("societe")
        val val5 = extras!!.getInt("nbpostes").toString()
        val val6 = extras!!.getString("pays")

        a.setText(val1)
        b.setText(val2)
        c.setText(val3)
        d.setText(val4)
        e.setText(val5)
        f.setText(val6)

        modifier = findViewById(R.id.modifier)
        modifier.setOnClickListener {
            if (TextUtils.isEmpty(a.text) && TextUtils.isEmpty(b.text)  && TextUtils.isEmpty(c.text)  && TextUtils.isEmpty(d.text)  && TextUtils.isEmpty(e.text)  && TextUtils.isEmpty(f.text) ) {
                Toast.makeText(this@MainActivityPUT, "no values ", Toast.LENGTH_SHORT).show();
            }
            updateData(
                a.text.toString().toInt(),
                b.text.toString(),
                c.text.toString(),
                d.text.toString(),
                e.text.toString().toInt(),
                f.text.toString()
            );

        }
    }
    private fun updateData(c : Int,sp : String,intu: String,sc : String,nb : Int,p : String ) {

        val postUrl = "http://192.168.43.35:8080/Offres/$c"
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

        val jsonObjectRequest = JsonObjectRequest(Request.Method.PUT, postUrl, Data, {
            Toast.makeText(this@MainActivityPUT, "updated succesfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivityPUT, MainActivity::class.java)
            startActivity(intent)


        }
        )
        { Toast.makeText(this@MainActivityPUT, "update failed", Toast.LENGTH_SHORT).show() }

        requestQueue.add(jsonObjectRequest)


    }
}