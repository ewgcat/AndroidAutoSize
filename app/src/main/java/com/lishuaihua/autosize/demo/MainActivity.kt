package com.lishuaihua.autosize.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lishuaihua.autosize.AutoSizeConfig
import com.lishuaihua.autosize.AutoSizeConfig.Companion.instance
import com.lishuaihua.autosize.unit.Subunits

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * 需要注意的是暂停 AndroidAutoSize 后, AndroidAutoSize 只是停止了对后续还没有启动的 [Activity] 进行适配的工作
     * 但对已经启动且已经适配的 [Activity] 不会有任何影响
     *
     * @param view [View]
     */
    fun stop(view: View?) {
        Toast.makeText(applicationContext, "AndroidAutoSize stops working!", Toast.LENGTH_SHORT).show()
        instance!!.stop(this)
    }

    /**
     * 需要注意的是重新启动 AndroidAutoSize 后, AndroidAutoSize 只是重新开始了对后续还没有启动的 [Activity] 进行适配的工作
     * 但对已经启动且在 stop 期间未适配的 [Activity] 不会有任何影响
     *
     * @param view [View]
     */
    fun restart(view: View?) {
        Toast.makeText(applicationContext, "AndroidAutoSize continues to work", Toast.LENGTH_SHORT).show()
        instance!!.restart()
    }

    /**
     * 跳转到 [CustomAdapterActivity], 展示项目内部的 [Activity] 自定义适配参数的用法
     *
     * @param view [View]
     */
    fun goCustomAdaptActivity(view: View?) {
        startActivity(Intent(applicationContext, CustomAdapterActivity::class.java))
    }
}