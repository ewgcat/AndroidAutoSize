package com.lishuaihua.autosize

import android.app.Activity

/**
 * ================================================
 * 屏幕适配逻辑策略类, 可通过 [AutoSizeConfig.init]
 * 和 [AutoSizeConfig.setAutoAdaptStrategy] 切换策略
 *
 * @see DefaultAutoAdaptStrategy
 *
 * ================================================
 */
interface AutoAdaptStrategy {
    /**
     * 开始执行屏幕适配逻辑
     *
     * @param target   需要屏幕适配的对象 (可能是 [Activity] 或者 Fragment)
     * @param activity 需要拿到当前的 [Activity] 才能修改 [DisplayMetrics.density]
     */
    fun applyAdapt(target: Any?, activity: Activity?)
}