package com.lishuaihua.autosize

import android.app.Activity
import com.lishuaihua.autosize.AutoSizeConfig.Companion.instance

/**
 * ================================================
 * [AutoAdaptStrategy] 的包装者, 用于给 [AutoAdaptStrategy] 的实现类增加一些额外的职责
 * ================================================
 */
class WrapperAutoAdaptStrategy(private val mAutoAdaptStrategy: AutoAdaptStrategy?) : AutoAdaptStrategy {
    override fun applyAdapt(target: Any?, activity: Activity?) {
        val onAdapterListener = instance!!.onAdaptListener
        onAdapterListener?.onAdapterBefore(target, activity)
        mAutoAdaptStrategy?.applyAdapt(target, activity)
        onAdapterListener?.onAdapterAfter(target, activity)
    }
}