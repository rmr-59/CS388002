package com.example.rameshrussellproject5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.rameshrussellproject5.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  var healths: MutableList<DisplayHealth> = mutableListOf()
    @SuppressLint("UnsafeIntentLaunch")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val fragmentManager: FragmentManager = supportFragmentManager
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val stats = intArrayOf(0,0,0)




        val healthFragment: Fragment = HealthFragment()
        val statsFragment: Fragment = StatsFragment(stats)
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_list -> fragment = healthFragment
                R.id.nav_stats -> fragment = statsFragment
            }
            lifecycleScope.launch {
                (application as HealthApplication).db.healthDao().getAll().collect{ databaseList ->
                    databaseList.map { entity ->
                        DisplayHealth(
                            entity.food,
                            entity.calories
                        )
                    }.also { mappedList ->
                        healths.clear()
                        healths.addAll(mappedList)
                        //healthAdapter.notifyDataSetChanged()

                    }
                }
            }
            if(healths.isNotEmpty()){
                var max = healths[0].calories?.toInt()?: 0
                var min = healths[0].calories?.toInt()?: 0
                var total = 0
                for (x in healths ){
                    var current = x.calories?.toInt()?: 0
                    total += current
                    if(current <min)
                        min= current
                    if(current >min)
                        max = current
                }
                total /= healths.size
                stats[0]=total
                stats[1]= min
                stats[2]= max

            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_list

    }



    private fun replaceFragment(health: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.health_frame_layout, health)
        fragmentTransaction.commit()
    }

}