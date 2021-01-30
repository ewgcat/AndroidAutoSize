package com.lishuaihua.autosize.demo

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.lishuaihua.autosize.AutoSize.Companion.autoConvertDensity
import com.lishuaihua.autosize.internal.CustomAdapter
import com.lishuaihua.autosize.utils.AutoSizeUtils.Companion.dp2px

class CustomFragment1 : Fragment(), CustomAdapter {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //由于某些原因, 屏幕旋转后 Fragment 的重建, 会导致框架对 Fragment 的自定义适配参数失去效果
        //所以如果您的 Fragment 允许屏幕旋转, 则请在 onCreateView 手动调用一次 AutoSize.autoConvertDensity()
        //如果您的 Fragment 不允许屏幕旋转, 则可以将下面调用 AutoSize.autoConvertDensity() 的代码删除掉
        autoConvertDensity(activity!!, 1080f, true)
        return createTextView(inflater, "Fragment-1\nView width = 360dp\nTotal width = 1080dp", -0x10000)
    }

    override val isBaseOnWidth: Boolean
        get() = true
    override val sizeInDp: Float
        get() = 1080f

    companion object {
        @JvmStatic
        fun createTextView(inflater: LayoutInflater, content: String?, backgroundColor: Int): View {
            val view = TextView(inflater.context)
            val layoutParams = ViewGroup.LayoutParams(dp2px(inflater.context, 360f),
                    ViewGroup.LayoutParams.MATCH_PARENT)
            view.layoutParams = layoutParams
            view.text = content
            view.setTextColor(-0x1)
            view.gravity = Gravity.CENTER
            view.textSize = 30f
            view.setBackgroundColor(backgroundColor)
            return view
        }
    }
}