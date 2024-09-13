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
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class thirdFragment : Fragment(R.layout.fragment_third) {

    lateinit var tv: TextView
    lateinit var rq: RequestQueue
    lateinit var btn: Button
    lateinit var et: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv = view.findViewById(R.id.textViewF3)
        btn = view.findViewById(R.id.buttonF3)
        et = view.findViewById(R.id.editTextF3)

        rq = Volley.newRequestQueue(context)

        btn.setOnClickListener {
            Log.i("Sami", "button clicked ")

            val rq: RequestQueue = Volley.newRequestQueue(context)

            //Get the input text and putting it in the URL for the search, trim incase you want to use lastname with space
            val input = et.text.toString().trim()
            val url = "https://api.genderize.io?name=${input}"

            val jsonRequest = JsonObjectRequest ( url,{
                    res ->
                Log.d("sami", res.toString())

                val gend = res.getString("gender")
                val prob = res.getDouble("probability")

                //using the * 100 so i can display it in % for a better look
                tv.text = "Name: $input \n Gender: $gend \n\n Probability: ${prob * 100}%"

            },
                { err ->  Log.e("sami",err.toString()) }
            )
            rq.add(jsonRequest)
        }
    }
}
