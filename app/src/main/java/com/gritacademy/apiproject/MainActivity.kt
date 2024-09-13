package com.gritacademy.apiproject
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        findViewById<Button>(R.id.button).setOnClickListener {
            Log.i("sami", "onCreate: "+navController.currentDestination!!.toString())

            Log.i("sami", "onCreate: "+navController.currentDestination!!.toString()+navController.currentDestination!!.id)

            //navigation checking current dest and depending on id it sends you to the next in order
            when (navController.currentDestination!!.id) {
                R.id.firstFragment -> navController.navigate(R.id.action_firstFragment_to_secondFragment)
                R.id.secondFragment -> navController.navigate(R.id.action_secondFragment_to_thirdFragment)
                R.id.thirdFragment -> navController.navigate(R.id.action_thirdFragment_to_firstFragment)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}