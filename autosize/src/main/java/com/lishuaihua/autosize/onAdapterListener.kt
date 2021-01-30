package com.lishuaihua.autosize

import android.app.Activity

/**
 * ================================================
 * 屏幕适配监听器，用于监听屏幕适配时的一些事件
 *
 * ================================================
 */
interface onAdapterListener {
    /**
     * 在屏幕适配前调用
     *
     * @param target   需要屏幕适配的对象 (可能是 [Activity] 或者 Fragment)
     * @param activity 当前 [Activity]
     */
    fun onAdapterBefore(target: Any?, activity: Activity?)

    /**
     * 在屏幕适配后调用
     *
     * @param target   需要屏幕适配的对象 (可能是 [Activity] 或者 Fragment)
     * @param activity 当前 [Activity]
     */
    fun onAdapterAfter(target: Any?, activity: Activity?)
}