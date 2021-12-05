package com.dev.a0303viewpager2kotlin

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_scale.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class ScaleFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scale, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        iv_scale.setOnClickListener {
            val value = if (Random.nextBoolean()) 0.1f else -0.1f
            ObjectAnimator.ofFloat(it, "scaleX", it.scaleX + value).start()
            ObjectAnimator.ofFloat(it, "scaleY", it.scaleY + value).start()
        }
    }
}
