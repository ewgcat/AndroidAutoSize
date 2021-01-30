package com.lishuaihua.autosize.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lishuaihua.autosize.internal.CustomAdapter

class CustomAdapterActivity : AppCompatActivity(), CustomAdapter {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_adapt)
    }

    /**
     * 跳转到 [FragmentHost], 展示项目内部的 [Fragment] 自定义适配参数的用法
     *
     * @param view [View]
     */
    fun goCustomAdaptFragment(view: View?) {
        startActivity(Intent(applicationContext, FragmentHost::class.java))
    }

    /**
     * 是否按照宽度进行等比例适配 (为了保证在高宽比不同的屏幕上也能正常适配, 所以只能在宽度和高度之中选择一个作为基准进行适配)
     *
     * @return `true` 为按照宽度进行适配, `false` 为按照高度进行适配
     */
    override val isBaseOnWidth: Boolean
        get() = false

    /**
     * 这里使用 iPhone 的设计图, iPhone 的设计图尺寸为 750px * 1334px, 高换算成 dp 为 667 (1334px / 2 = 667dp)
     *
     *
     * 返回设计图上的设计尺寸, 单位 dp
     * [.getSizeInDp] 须配合 [.isBaseOnWidth] 使用, 规则如下:
     * 如果 [.isBaseOnWidth] 返回 `true`, [.getSizeInDp] 则应该返回设计图的总宽度
     * 如果 [.isBaseOnWidth] 返回 `false`, [.getSizeInDp] 则应该返回设计图的总高度
     * 如果您不需要自定义设计图上的设计尺寸, 想继续使用在 AndroidManifest 中填写的设计图尺寸, [.getSizeInDp] 则返回 `0`
     *
     * @return 设计图上的设计尺寸, 单位 dp
     */
    override val sizeInDp: Float
        get() = 667f
}