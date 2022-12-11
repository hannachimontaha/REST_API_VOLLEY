package com.example.rest_api_volley

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    lateinit var data : MutableList<Offre>
    lateinit var ajouter : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getData()
        ajouter = findViewById(R.id.ajout)
        ajouter.setOnClickListener{

            val intent = Intent(applicationContext, MainActivityPOST::class.java)
            startActivity(intent)
        }

    }

    private fun getData() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // on below line we are creating a variable for url
        var url = "http://192.168.43.35:8080/Offres"

        // on below line we are creating a
        // variable for our request queue
        val queue = Volley.newRequestQueue(this)


        // on below line we are creating a request
        // variable for making our json object request.
        val request = JsonArrayRequest(Request.Method.GET, url, null, { response ->
            // this method is called when we get successful response from API.
            try {
                data = mutableListOf()
                for (i in 0 until response.length()) {
                    val respObj = response.getJSONObject(i)
                    val offre = Offre()
                    recyclerView.apply {
                        offre.code = respObj.getInt("code")
                        offre.intitulé = respObj.getString("intitulé")
                        offre.specialité = respObj.getString("specialité")
                        offre.société = respObj.getString("société")
                        offre.nbpostes = respObj.getInt("nbpostes")
                        offre.pays = respObj.getString("pays")

                        data.add(offre)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = OffreAdapter(data) }

                    //Log.i("Success", respObj.toString());
                }
                // on below line we
                // are handling exception
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }, { error ->
            // in this case we are simply displaying a toast message.
            //Toast.makeText(this, "Fail to get response", Toast.LENGTH_SHORT).show()
            Log.e("request-error", error.toString());
        })
        // at last we are adding our
        // request to our queue.

        queue.add(request)
    }

}