package com.example.create_account

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.accountcreate.R

class FragmentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragments)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace<FragmentSignIn>(R.id.fragment_container_view)
            }
        }
    }

    fun switchToRegister() {
        supportFragmentManager.commit {
            replace<FragmentRegister>(R.id.fragment_container_view)
            addToBackStack(null)
        }
    }

    fun switchToLogin() {
        supportFragmentManager.commit {
            replace<FragmentSignIn>(R.id.fragment_container_view)
            addToBackStack(null)
        }
    }
}
