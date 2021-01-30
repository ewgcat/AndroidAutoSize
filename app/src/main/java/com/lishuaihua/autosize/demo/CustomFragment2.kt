package com.lishuaihua.autosize.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lishuaihua.autosize.AutoSize.Companion.autoConvertDensity
import com.lishuaihua.autosize.demo.CustomFragment1.Companion.createTextView
import com.lishuaihua.autosize.internal.CustomAdapter

class CustomFragment2 : Fragment(), CustomAdapter {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //由于某些原因, 屏幕旋转后 Fragment 的重建, 会导致框架对 Fragment 的自定义适配参数失去效果
        //所以如果您的 Fragment 允许屏幕旋转, 则请在 onCreateView 手动调用一次 AutoSize.autoConvertDensity()
        //如果您的 Fragment 不允许屏幕旋转, 则可以将下面调用 AutoSize.autoConvertDensity() 的代码删除掉
        autoConvertDensity(activity!!, 720f, true)
        return createTextView(inflater, "Fragment-2\nView width = 360dp\nTotal width = 720dp", -0xff0100)
    }

    override val isBaseOnWidth: Boolean
        get() = true
    override val sizeInDp: Float
        get() = 720f
}