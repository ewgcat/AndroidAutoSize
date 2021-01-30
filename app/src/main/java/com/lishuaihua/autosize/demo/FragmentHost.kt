package com.lishuaihua.autosize.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lishuaihua.autosize.internal.CustomAdapter

class FragmentHost : AppCompatActivity(), CustomAdapter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        if (supportFragmentManager.findFragmentById(R.id.container1) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container1, CustomFragment1()).commit()
        }
        if (supportFragmentManager.findFragmentById(R.id.container2) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container2, CustomFragment2()).commit()
        }
        if (supportFragmentManager.findFragmentById(R.id.container3) == null) {
            supportFragmentManager.beginTransaction().add(R.id.container3, CustomFragment3()).commit()
        }
    }

    override val isBaseOnWidth: Boolean
        get() = true
    override val sizeInDp: Float
        get() = 720f
}