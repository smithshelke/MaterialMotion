package com.smith.materialmotion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        setupInsets(v)
        return v
    }

    private fun setupInsets(v: View) {
        v.toolbar.setOnApplyWindowInsetsListener { _, insets ->
            v.toolbar.setContentPadding(0,insets.systemWindowInsetTop,0,0)
            insets
        }
    }

}