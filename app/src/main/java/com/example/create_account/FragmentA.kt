package com.example.create_account

import androidx.fragment.app.Fragment
import com.example.accountcreate.R

class FragmentA : Fragment(R.layout.fragment_a) {

    private var eventListener: EventListener? = null

    interface EventListener {
        fun onGoToBPressed()
    }
}