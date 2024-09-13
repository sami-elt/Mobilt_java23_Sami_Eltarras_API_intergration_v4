package com.gritacademy.apiproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class FirstFragment : Fragment(R.layout.fragment_first) {
    lateinit var tv: TextView
    lateinit var rq: RequestQueue
    lateinit var btn: Button
    lateinit var et: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        tv = view.findViewById(R.id.textView4)
        btn = view.findViewById(R.id.button2)
        et = view.findViewById(R.id.editTextText)

        rq = Volley.newRequestQueue(context)

     btn.setOnClickListener {
         Log.i("Sami", "button clicked ")

         val rq: RequestQueue = Volley.newRequestQueue(context)

         //Get the input text and putting it in the URL for the search
         val input = et.text.toString().trim()
         val url = "https://api.nationalize.io/?name=${input}"


         var jsonRequest = JsonObjectRequest ( url,{
                 res ->
             Log.d("Sami", res.toString())
                //just getting the first country in the object
                 val country: JSONObject = res.getJSONArray("country").getJSONObject(0)

                 Log.d("sami", " first: " + country)

                 //getting the object that contains country_id
                 val countr = country.get("country_id")

                 Log.d("sami", "countries: " + countr)
                 tv.text = "Name: $input \n The name is most likely from: \n $countr"


         },
             { err ->  Log.e("sami",err.toString()) }
         )
         rq.add(jsonRequest)
     }

    }
}