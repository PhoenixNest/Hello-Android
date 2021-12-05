package com.dev.a0303viewpager2kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp_main.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> ScaleFragment()
                    1 -> RotateFragment()
                    else -> TranslateFragment()
                }
            }
        }

        TabLayoutMediator(tl_main, vp_main) { tab, position ->
            when (position) {
                0 -> tab.text = "Scale"
                1 -> tab.text = "Rotate"
                else -> tab.text = "Translate"
            }
        }.attach()
    }
}
