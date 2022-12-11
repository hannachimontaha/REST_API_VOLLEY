package com.example.rest_api_volley

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


internal class OffreAdapter (private val data : MutableList<Offre>) : RecyclerView.Adapter<OffreAdapter.MyViewHolder>() {
     var selectedItemPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ligne,parent,false)
        return  MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val offre : Offre = data[position]
        holder.bindView(offre)

        holder.itemView.setOnClickListener {
            selectedItemPosition = position
            notifyDataSetChanged()
        }
        if( selectedItemPosition == position)
            holder.code.setBackgroundColor(Color.parseColor("#DC746C"))
        //holder.cardView.setBackgroundColor(Color.parseColor("#DC746C"))
        else
            holder.code.setBackgroundColor(Color.parseColor("#ffffff"))
            //holder.cardView.setBackgroundColor(Color.parseColor("#ffffff"))
    }


    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        //récuperer tous le contenu de ligne.xml
        var cardView : CardView = view.findViewById(R.id.card_view)
        var code : TextView = view.findViewById(R.id.code)
        private var intitulé : TextView = view.findViewById(R.id.intitulé)
        private var specialité : TextView = view.findViewById(R.id.specialité)
        private var société : TextView = view.findViewById(R.id.société)
        private var nbpostes : TextView = view.findViewById(R.id.nbpostes)
        private var pays : TextView = view.findViewById(R.id.pays)
        private var btn : Button = view.findViewById(R.id.delete)
        private var edit : Button = view.findViewById(R.id.edit)


        fun bindView(offre: Offre){
            code.text = "code : "+ offre.code.toString()
            intitulé.text ="intitulé : "+ offre.intitulé
            specialité.text ="specialité : "+ offre.specialité
            société.text ="société : "+ offre.société
            nbpostes.text ="nbpostes : "+ offre.nbpostes.toString()
            pays.text ="pays : "+ offre.pays

            btn.setOnClickListener { v ->
                val c = offre.code
                var url = "http://192.168.43.35:8080/Offres/$c"
                val queue = Volley.newRequestQueue(v.context)
                val request =object : StringRequest(Request.Method.DELETE, url, Response.Listener { response ->
                        Toast.makeText(v.context, "photo supprimer", Toast.LENGTH_SHORT).show();
                        data.removeAt(adapterPosition)
                        notifyDataSetChanged()
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(v.context, "Erroooooor", Toast.LENGTH_SHORT).show();
                    }){ }
                queue.add(request)
            }
            edit.setOnClickListener{ v->
                val intent = Intent(v.context, MainActivityPUT::class.java)
                intent.putExtra("code", offre.code)
                intent.putExtra("intitule", offre.intitulé)
                intent.putExtra("specialite", offre.specialité)
                intent.putExtra("societe", offre.société)
                intent.putExtra("nbpostes", offre.nbpostes)
                intent.putExtra("pays", offre.pays)

                v.context.startActivity(intent)
            }
                    }

                }
            }







