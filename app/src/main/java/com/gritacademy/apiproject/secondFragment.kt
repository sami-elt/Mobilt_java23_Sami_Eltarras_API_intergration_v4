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


class secondFragment : Fragment(R.layout.fragment_second) {

    lateinit var tv: TextView
    lateinit var rq: RequestQueue
    lateinit var btn: Button
    lateinit var et: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv = view.findViewById(R.id.textViewF2)
        btn = view.findViewById(R.id.buttonF2)
        et = view.findViewById(R.id.editTextF2)

        rq = Volley.newRequestQueue(context)

        btn.setOnClickListener {
            Log.i("Sami", "button clicked ")

            val rq: RequestQueue = Volley.newRequestQueue(context)

            //Get the input text and putting it in the URL for the search
            val input = et.text.toString().trim()
            val url = "https://api.nationalize.io/?name=${input}"

            //empty string that i can use to display data on my textview
            var data = ""

            //getting the array and looping through the objects in it
            val jsonRequest = JsonObjectRequest ( url,{
                    res ->
                Log.d("Sami", res.toString())
                val countries: JSONArray = res.getJSONArray("country");

                for (i in 0 until countries.length()){
                    val countriesObject: JSONObject = countries.getJSONObject(i)
                    Log.d("sami", " $countriesObject")

                    //getting the objects that contains country_id and probabilty
                    val countr = countriesObject.get("country_id")
                    val prob = countriesObject.get("probability")

                    Log.d("sami", "countries: " + countr + " probabilties: " + prob)

                    //adding data to my string and then putting it in textview
                    data += "country: $countr \n probability: $prob \n\n"
                    tv.text = "Name: $input \n" + data
                }
            },
                { err ->  Log.e("sami",err.toString()) }
            )
            rq.add(jsonRequest)
        }
    }

}